package ml.ledv.library.db.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ml.ledv.library.db.common.repository.jpa")
@EnableMongoRepositories(basePackages = "ml.ledv.library.db.common.repository.mongo")
public class RepositoryConfig {
}
