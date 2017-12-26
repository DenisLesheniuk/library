package ml.ledv.library.db.nosql.repository;

import ml.ledv.library.db.nosql.entity.impl.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByLogin(final String login);

    void deleteById(final String id);

}
