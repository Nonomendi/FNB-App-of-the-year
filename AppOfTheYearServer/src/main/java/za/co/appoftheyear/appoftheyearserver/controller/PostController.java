package za.co.appoftheyear.appoftheyearserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.appoftheyear.appoftheyearserver.dao.PostDao;
import za.co.appoftheyear.appoftheyearserver.entity.Post;
import za.co.appoftheyear.appoftheyearserver.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPost(@PathVariable long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @GetMapping("title/{title}")
    public ResponseEntity<Post> getPost(@PathVariable String title) {
        return ResponseEntity.ok(postService.getPost(title));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDao post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createJobPost(post));
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@PathVariable long id, @RequestBody PostDao post) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(postService.updatePost(id, post));
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }
}