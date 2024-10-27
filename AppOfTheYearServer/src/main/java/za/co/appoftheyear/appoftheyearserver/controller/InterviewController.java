package za.co.appoftheyear.appoftheyearserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.appoftheyear.appoftheyearserver.dao.InterviewDao;
import za.co.appoftheyear.appoftheyearserver.service.InterviewService;

@RestController
@RequestMapping("/api/v1/interviews")
public class InterviewController {

    private InterviewService service;

    @Autowired
    public InterviewController(InterviewService service) {
        this.service = service;
    }

    @PostMapping("{id}")
    public ResponseEntity<String> scheduleInterview(@PathVariable String id, @RequestBody InterviewDao dao) {
        return ResponseEntity.ok(service.mockInterview(id, dao));
    }
}