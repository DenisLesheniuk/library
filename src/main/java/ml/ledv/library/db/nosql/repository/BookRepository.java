package ml.ledv.library.db.nosql.repository;

import ml.ledv.library.db.nosql.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> getBooksByUserIsNull();
}
