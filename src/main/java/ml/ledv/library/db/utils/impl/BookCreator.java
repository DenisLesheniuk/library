package ml.ledv.library.db.utils.impl;

import ml.ledv.library.db.entity.BaseEntity;
import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.utils.EntityCreator;
import org.springframework.stereotype.Service;

@Service("bookCreator")
public class BookCreator implements EntityCreator {

    @Override
    public BaseEntity createEntity() {
        return new BookEntity();
    }
}
