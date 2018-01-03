package ml.ledv.library.db.repository;

import ml.ledv.library.db.CommonBookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommonBookRepository extends PagingAndSortingRepository<CommonBookEntity, String> {

    List<CommonBookEntity> getCommonBookEntitiesByUserIsNull();
}
