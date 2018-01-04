package ml.ledv.library.db.repository;

import ml.ledv.library.db.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {
}
