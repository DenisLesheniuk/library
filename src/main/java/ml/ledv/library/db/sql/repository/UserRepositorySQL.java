package ml.ledv.library.db.sql.repository;

import ml.ledv.library.db.sql.entity.impl.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepositorySQL extends PagingAndSortingRepository<UserEntity, String> {
}
