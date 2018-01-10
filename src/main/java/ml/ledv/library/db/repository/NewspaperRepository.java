package ml.ledv.library.db.repository;

import ml.ledv.library.db.entity.impl.NewspaperEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewspaperRepository extends PagingAndSortingRepository<NewspaperEntity, String> {
}
