package ml.ledv.library.db.repository;

import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.entity.impl.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {

    Optional<UserEntity> findByBooks(BookEntity bookEntity);
}
