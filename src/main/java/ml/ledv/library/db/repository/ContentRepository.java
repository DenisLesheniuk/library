package ml.ledv.library.db.repository;

import ml.ledv.library.db.entity.impl.ContentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContentRepository extends PagingAndSortingRepository<ContentEntity, String> {
}
