package ml.ledv.library.db.sql.repository;

import ml.ledv.library.db.sql.entity.impl.BookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<BookEntity, String> {
}
