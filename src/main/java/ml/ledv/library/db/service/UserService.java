package ml.ledv.library.db.service;

import ml.ledv.library.db.entity.content.BookEntity;
import ml.ledv.library.db.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(String login);

    void deleteUser(UserEntity user);

    Optional<UserEntity> getUserById(String id);

    void updateUser(UserEntity user);

    List<UserEntity> getAll();

    void addBook(UserEntity user, BookEntity book);

    void removeBook(UserEntity user, BookEntity book);

    Optional<UserEntity> getUserByBook(BookEntity bookEntity);
}
