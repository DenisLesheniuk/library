package ml.ledv.library.db.repository;

import ml.ledv.library.db.entity.BookEntity;
import ml.ledv.library.db.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByBooks(BookEntity bookEntity);
}
