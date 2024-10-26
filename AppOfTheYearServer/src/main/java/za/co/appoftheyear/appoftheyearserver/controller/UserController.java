package za.co.appoftheyear.appoftheyearserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.appoftheyear.appoftheyearserver.entity.User;
import za.co.appoftheyear.appoftheyearserver.service.UserService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userService.getUsers());
    }
}