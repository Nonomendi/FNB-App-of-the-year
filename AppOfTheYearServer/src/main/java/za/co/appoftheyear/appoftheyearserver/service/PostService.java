package za.co.appoftheyear.appoftheyearserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.appoftheyear.appoftheyearserver.dao.PostDao;
import za.co.appoftheyear.appoftheyearserver.entity.Post;
import za.co.appoftheyear.appoftheyearserver.repo.PostRepo;

import java.util.List;

@Service
public class PostService {

    private PostRepo repo;

    @Autowired
    public PostService(PostRepo repo) {
        this.repo = repo;
    }

    public Post createJobPost(PostDao postDao) {
        Post post = new Post(postDao.getTitle(), postDao.getDescription(), postDao.getApplicationLink());
        return repo.save(post);
    }

    public Post getPost(long id) {
        return repo.findById(id).orElseThrow(() -> new UnsupportedOperationException("Unknown job id '" + id + "'"));
    }

    public Post getPost(String title) {
        return repo.findByTitleContaining(title).orElseThrow(() -> new UnsupportedOperationException("Cannot find any post matching '" + title + "'"));
    }

    public List<Post> getAllPosts() {
        return repo.findAll();
    }

    public Post updatePost(long id, PostDao dao) {
        Post post = getPost(id);
        post.setTitle(dao.getTitle());
        post.setDescription(dao.getDescription());
        post.setApplicationLink(dao.getApplicationLink());
        return repo.save(post);
    }

    public void deletePost(long id) {
        Post post = getPost(id);
        repo.delete(post);
    }
}