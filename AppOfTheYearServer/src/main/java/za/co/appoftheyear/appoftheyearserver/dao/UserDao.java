package za.co.appoftheyear.appoftheyearserver.dao;

import lombok.Data;

@Data
public class UserDao {

    private String username;
    private String email;
    private String password;
}