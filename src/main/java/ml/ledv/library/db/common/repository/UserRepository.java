package ml.ledv.library.db.common.repository;

import ml.ledv.library.db.common.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {
}
