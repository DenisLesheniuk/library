package ml.ledv.library.db.common.repository.mongo;

import ml.ledv.library.db.common.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MongoUserRepository extends PagingAndSortingRepository<UserEntity, String> {


}
