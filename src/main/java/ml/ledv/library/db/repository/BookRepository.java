package ml.ledv.library.db.repository;

import ml.ledv.library.db.entity.impl.BookEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends PagingAndSortingRepository<BookEntity, String> {

    @Transactional
    @Modifying
    @Query("update BOOK b set b.id = :newId where b.id = :id")
    void updateId(@Param("newId") String newId,@Param("id") String id);
}
