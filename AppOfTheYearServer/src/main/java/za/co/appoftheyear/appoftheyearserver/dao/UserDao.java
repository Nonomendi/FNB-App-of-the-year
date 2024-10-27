package za.co.appoftheyear.appoftheyearserver.dao;

import lombok.Data;
import za.co.appoftheyear.appoftheyearserver.enums.Competency;

@Data
public class UserDao {

    private String username;
    private String email;
    private String password;
    private String targetPosition;
    private Competency proficiency;
}