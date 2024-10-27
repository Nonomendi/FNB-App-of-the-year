package za.co.appoftheyear.appoftheyearserver.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.appoftheyear.appoftheyearserver.dao.InterviewDao;
import za.co.appoftheyear.appoftheyearserver.entity.Interview;
import za.co.appoftheyear.appoftheyearserver.entity.User;
import za.co.appoftheyear.appoftheyearserver.enums.Competency;
import za.co.appoftheyear.appoftheyearserver.repo.InterviewRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Service
public class InterviewService {

    private InterviewRepo repo;
    private UserService userService;

    @Autowired
    public InterviewService(InterviewRepo repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    public String mockInterview(String id, InterviewDao interviewDao) {
        long count = repo.findAll().stream().filter(x -> x.getUser().getId().equals(id)).count();
        User user = userService.getUser(id);
        Interview interview;
        if (count == 0) {
            String initialize = getString(user);
            interview = new Interview(initialize, user);
        } else {
            interview = new Interview(interviewDao.getMessage(), user);
        }
        repo.save(interview);

        List<String> allMessages = repo.findAll().stream().filter(x -> x.getUser().getId().equals(id))
                .map(x -> x.getMessage()).toList();

        allMessages.forEach(System.out::println);

        List<Map<String, String>> messages = new ArrayList<>();

        for(int i = 0; i < allMessages.size(); i++) {
            Map<String, String> request = new HashMap<>();
            if ((i % 2) == 0) {
                request.put("role", "user");
            } else {
                request.put("role", "assistant");
            }
            request.put("content", allMessages.get(i));
            messages.add(request);
        }

        String response = requestQuestion(messages);
        interview = new Interview(response, user);

        if (response.length() > 1200) {
            List<Interview> questions = repo.findAll().stream().filter(x -> x.getUser().getId().equals(id)).toList();
            repo.deleteAll(questions);
        } else {
            repo.save(interview);
        }
        return interview.getMessage();
    }

    private static String getString(User user) {
        String position = user.getTargetPosition();
        Competency competency = user.getProficiency();
        String initialize;

        if (!(position == null && competency == null)) {
            initialize = String.format("Role play, as an interviewee, ask me questions for a %s to identify potential to join " +
                    "your company as a %s and rate response at the end of the interview and advise if you would" +
                    " hire me or not and why and I will respond as an interviewee", position, competency.toString());
        } else if (position != null) {
            initialize = String.format("Role play, as an interviewee, ask me questions for a %s and rate response at the end of the interview and advise if you would" +
                    " hire me or not and why and I will respond as an interviewee", position);
        } else {
            initialize = "Role play, as an interviewee, and rate response at the end of the interview and advise if you would" +
                    " hire me or not and why and I will respond as an interviewee";
        }
        return initialize;
    }

    private String requestQuestion(List<Map<String, String>> body) {
        Dotenv env = Dotenv.load();
        String token = env.get("SECRET_KEY");

        RestAssured.baseURI = "https://api.openai.com/v1/chat/completions";
        Map<String, Object> request = getStringObjectMap(body);

        Gson json = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(">>>: " + json.toJson(request));

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Bearer %s", token))
                .when()
                .body(json.toJson(request))
                .post();

        Map<String, Object> message = ((ArrayList<Map<String, Object>>) response.body().as(Map.class).get("choices")).get(0);
        return ((Map<String, String>) message.get("message")).get("content");
    }

    private static Map<String, Object> getStringObjectMap(List<Map<String, String>> messages) {
        Map<String, Object> request = Map.of(
                "model", "gpt-4o-mini",
                "messages", messages
        );
        return request;
    }
}