package ml.ledv.library.db.common.repository.jpa;

import ml.ledv.library.db.common.entity.BookEntity;
import ml.ledv.library.db.common.repository.BookRepository;

import java.util.List;

public interface JpaBookRepository extends BookRepository {

    List<BookEntity> getCommonBookEntitiesByUserIsNull();
}
