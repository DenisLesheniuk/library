package ml.ledv.library.db.common.repository.mongo;

import ml.ledv.library.db.common.entity.BookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MongoBookRepository extends PagingAndSortingRepository<BookEntity, String> {

    List<BookEntity> getCommonBookEntitiesByUserIsNull();
}
