package za.co.appoftheyear.appoftheyearserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import za.co.appoftheyear.appoftheyearserver.enums.Competency;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String email;
    private String password;
    private String targetPosition;
    @Enumerated
    private Competency proficiency;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy ="user", orphanRemoval = true)
    private List<Interview> messages;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, String targetPosition, Competency proficiency) {
        this(username, email, password);
        this.targetPosition = targetPosition;
        this.proficiency = proficiency;
    }
}