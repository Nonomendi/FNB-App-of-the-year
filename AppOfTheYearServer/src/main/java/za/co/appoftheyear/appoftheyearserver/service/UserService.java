package za.co.appoftheyear.appoftheyearserver.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import za.co.appoftheyear.appoftheyearserver.dao.UserDao;
import za.co.appoftheyear.appoftheyearserver.entity.User;
import za.co.appoftheyear.appoftheyearserver.repo.UserRepo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private UserRepo repo;

    @Autowired
    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public User createUser(UserDao userDao) {
        User user = new User(userDao.getUsername(), userDao.getEmail(), userDao.getPassword(), userDao.getTargetPosition(), userDao.getProficiency());
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUser(String id) {
        return repo.findById(id).orElseThrow(() -> new UnsupportedOperationException("User id does not exist '" + id + "'"));
    }

    public User updateUser(String id, UserDao userDao) {
        User user = getUser(id);
        user.setUsername(userDao.getUsername());
        user.setEmail(userDao.getEmail());
        user.setPassword(userDao.getPassword());
        return repo.save(user);
    }

    public void deleteUser(String id) {
        User user = getUser(id);
        repo.delete(user);
    }
}