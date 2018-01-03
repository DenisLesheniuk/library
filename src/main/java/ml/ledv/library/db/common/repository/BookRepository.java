package ml.ledv.library.db.common.repository;

import ml.ledv.library.db.common.entity.BookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<BookEntity, String> {
    List<BookEntity> getCommonBookEntitiesByUserIsNull();
}
