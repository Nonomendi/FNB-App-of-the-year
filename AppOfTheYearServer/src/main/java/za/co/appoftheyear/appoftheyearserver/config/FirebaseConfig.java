//package za.co.appoftheyear.appoftheyearserver.config;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.firestore.Firestore;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.cloud.FirestoreClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//@Configuration
//public class FirebaseConfig {
//
//    @Bean
//    public Firestore firestore() throws IOException {
////        if (FirebaseApp.getApps().isEmpty()) {
//            FileInputStream serviceAccount = new FileInputStream("firestoredb.json");
//
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//
//            FirebaseApp.initializeApp(options);
////        }
//        return FirestoreClient.getFirestore();
//    }
//}