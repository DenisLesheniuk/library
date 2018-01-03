package ml.ledv.library.db.common.repository.jpa;

import ml.ledv.library.db.common.entity.CommonUserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JpaUserRepository extends PagingAndSortingRepository<CommonUserEntity, String> {


}
