package ml.ledv.library.db.repository;

import ml.ledv.library.db.entity.content.BookEntity;
import ml.ledv.library.db.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {

    Optional<UserEntity> findByBooks(BookEntity bookEntity);
}
