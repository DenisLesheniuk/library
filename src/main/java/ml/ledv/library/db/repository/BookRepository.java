package ml.ledv.library.db.repository;

import ml.ledv.library.db.entity.BookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<BookEntity, String> {
}
