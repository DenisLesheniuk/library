package ml.ledv.library.db.nosql.service;


import ml.ledv.library.db.nosql.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(String login);

    void deleteUser(User user);

    Optional<User> getUserById(String id);

    void updateUser(User user);

    List<User> getAll();

}
