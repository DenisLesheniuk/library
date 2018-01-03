package ml.ledv.library.db.common.service;

import ml.ledv.library.db.common.entity.CommonUserEntity;

import java.util.List;
import java.util.Optional;

public interface CommonUserService {

    void createUser(String login);

    void deleteUser(CommonUserEntity user);

    Optional<CommonUserEntity> getUserById(String id);

    void updateUser(CommonUserEntity user);

    List<CommonUserEntity> getAll();
}
