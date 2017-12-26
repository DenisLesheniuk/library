package ml.ledv.library.db.nosql.repository;

import ml.ledv.library.db.nosql.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    void deleteById(String id);

}
