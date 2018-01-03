package ml.ledv.library.db.common.repository.mongo;

import ml.ledv.library.db.common.entity.CommonBookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MongoBookRepository extends PagingAndSortingRepository<CommonBookEntity, String> {

    List<CommonBookEntity> getCommonBookEntitiesByUserIsNull();
}
