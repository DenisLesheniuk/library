package ml.ledv.library.db.repository;

import ml.ledv.library.db.CommonUserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommonUserRepository extends PagingAndSortingRepository<CommonUserEntity, String> {


}
