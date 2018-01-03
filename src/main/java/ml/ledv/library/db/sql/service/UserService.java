package ml.ledv.library.db.sql.service;

import ml.ledv.library.db.CommonUserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(String login);

    void deleteUser(CommonUserEntity user);

    Optional<CommonUserEntity> getUserById(String id);

    void updateUser(CommonUserEntity user);

    List<CommonUserEntity> getAll();
}
