package ml.ledv.library.db.utils.impl;

import ml.ledv.library.db.entity.impl.AuthorEntity;
import ml.ledv.library.db.entity.BaseEntity;
import ml.ledv.library.db.utils.EntityCreator;
import org.springframework.stereotype.Service;

@Service
public class AuthorCreator implements EntityCreator {
    @Override
    public BaseEntity createEntity() {
        return new AuthorEntity();
    }
}
