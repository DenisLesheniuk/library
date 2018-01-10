package ml.ledv.library.db.repository;

import ml.ledv.library.db.entity.impl.AuthorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends PagingAndSortingRepository<AuthorEntity, String> {
}
