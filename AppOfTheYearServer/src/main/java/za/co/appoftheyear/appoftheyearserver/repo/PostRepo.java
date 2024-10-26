package za.co.appoftheyear.appoftheyearserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.appoftheyear.appoftheyearserver.entity.Post;

import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    Optional<Post> findByTitleContaining(String title);
}
