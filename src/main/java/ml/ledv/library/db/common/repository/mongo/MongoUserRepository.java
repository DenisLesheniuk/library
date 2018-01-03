package ml.ledv.library.db.common.repository.mongo;

import ml.ledv.library.db.common.entity.CommonUserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MongoUserRepository extends PagingAndSortingRepository<CommonUserEntity, String> {


}
