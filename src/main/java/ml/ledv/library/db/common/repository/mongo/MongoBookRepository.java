package ml.ledv.library.db.common.repository.mongo;

import ml.ledv.library.db.common.entity.BookEntity;
import ml.ledv.library.db.common.repository.BookRepository;

import java.util.List;

public interface MongoBookRepository extends BookRepository {

    List<BookEntity> getCommonBookEntitiesByUserIsNull();
}
