package za.co.appoftheyear.appoftheyearserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.appoftheyear.appoftheyearserver.entity.Interview;

@Repository
public interface InterviewRepo extends JpaRepository<Interview, Long> {
}