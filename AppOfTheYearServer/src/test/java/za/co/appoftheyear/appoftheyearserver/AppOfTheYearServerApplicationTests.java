package za.co.appoftheyear.appoftheyearserver;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@SpringBootTest
class AppOfTheYearServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testMentor() {
        Dotenv env = Dotenv.load();
        String token = env.get("SECRET_KEY");

        RestAssured.baseURI = "https://api.openai.com/v1/chat/completions";
        Map<String, Object> request = getStringObjectMap();

        Gson json = new Gson();

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Bearer %s", token))
                .when()
                .body(json.toJson(request))
                .post();

        Map<String, Object> message = ((ArrayList<Map<String, Object>>) response.body().as(Map.class).get("choices")).get(0);
        System.out.println(">>>: " + message.get("message"));
    }

    private static Map<String, Object> getStringObjectMap() {
        String level = "Junior";
        String position = "QA tester";

        Map<String, Object> request = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", String.format("Role play, as an interviewee, ask me questions for a %s to identify potential to join " +
                                        "your company as a %s and rate response at the end of the interview and advise if you would" +
                                        " hire me or not and why and I will respond as an interviewee", level, position)
                        )
                )
        );
        return request;
    }
}
