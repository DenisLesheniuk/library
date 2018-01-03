package ml.ledv.library.db.common.repository.jpa;

import ml.ledv.library.db.common.entity.BookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface JpaBookRepository extends PagingAndSortingRepository<BookEntity, String> {

    List<BookEntity> getCommonBookEntitiesByUserIsNull();
}
