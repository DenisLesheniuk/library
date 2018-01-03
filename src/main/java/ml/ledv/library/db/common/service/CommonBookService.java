package ml.ledv.library.db.common.service;

import ml.ledv.library.db.common.entity.CommonBookEntity;

import java.util.List;
import java.util.Optional;

public interface CommonBookService {

    void createBook(String name);

    void deleteBook(CommonBookEntity book);

    List<CommonBookEntity> getAll();

    List<CommonBookEntity> getAllFree();

    Optional<CommonBookEntity> getBookById(String id);

    void updateBook(CommonBookEntity book);

    void removeUser(CommonBookEntity book);
}
