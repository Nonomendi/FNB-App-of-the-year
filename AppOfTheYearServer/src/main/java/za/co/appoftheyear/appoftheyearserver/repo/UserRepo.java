package za.co.appoftheyear.appoftheyearserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.appoftheyear.appoftheyearserver.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
}
