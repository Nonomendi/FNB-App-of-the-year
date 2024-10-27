package za.co.appoftheyear.appoftheyearserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 10000)
    private String message;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    public User user;

    public Interview(String message, User user) {
        this.message = message;
        this.user = user;
    }
}