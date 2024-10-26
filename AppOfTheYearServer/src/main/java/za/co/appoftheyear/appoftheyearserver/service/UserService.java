package za.co.appoftheyear.appoftheyearserver.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.appoftheyear.appoftheyearserver.entity.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final String TABLE_NAME = "users";
    private Firestore firestore;

    @Autowired
    public UserService(Firestore firestore) {
        this.firestore = firestore;
    }

    public String createUser(User user) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> users = firestore.collection(TABLE_NAME).add(user);
        return users.get().getId();
    }

    public User getUser(String userId) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> users = firestore.collection(TABLE_NAME).document(userId).get();
        return users.get().toObject(User.class);
    }

    public List<User> getUsers() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> users = firestore.collection(TABLE_NAME).get();
        return users.get().toObjects(User.class);
    }
}