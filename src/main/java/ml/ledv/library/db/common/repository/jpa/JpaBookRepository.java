package ml.ledv.library.db.common.repository.jpa;

import ml.ledv.library.db.common.entity.CommonBookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface JpaBookRepository extends PagingAndSortingRepository<CommonBookEntity, String> {

    List<CommonBookEntity> getCommonBookEntitiesByUserIsNull();
}
