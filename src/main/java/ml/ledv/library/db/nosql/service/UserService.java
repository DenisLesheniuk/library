package ml.ledv.library.db.nosql.service;


import ml.ledv.library.db.nosql.entity.UserDocument;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(String login);

    void deleteUser(UserDocument userDocument);

    Optional<UserDocument> getUserById(String id);

    void updateUser(UserDocument userDocument);

    List<UserDocument> getAll();
}
