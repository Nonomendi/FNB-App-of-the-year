package za.co.appoftheyear.appoftheyearserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private String applicationLink;

    @CreationTimestamp
    private LocalDate createdAt;

    public Post(String title, String description, String applicationLink) {
        this.title = title;
        this.description = description;
        this.applicationLink = applicationLink;
    }
}