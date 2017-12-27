package ml.ledv.library.db.nosql.repository;

import ml.ledv.library.db.nosql.entity.BookDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<BookDocument, String> {

    List<BookDocument> getBooksByUserDocumentIsNull();
}
