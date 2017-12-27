package ml.ledv.library.db.sql.repository;

import ml.ledv.library.db.sql.entity.impl.BookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepositorySQL extends PagingAndSortingRepository<BookEntity, String> {

    List<BookEntity> getBookEntitiesByUserEntityIsNull();
}
