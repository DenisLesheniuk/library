package ml.ledv.library.db.utils.impl;

import ml.ledv.library.db.entity.BaseEntity;
import ml.ledv.library.db.entity.ContentEntity;
import ml.ledv.library.db.utils.EntityCreator;
import org.springframework.stereotype.Service;

@Service("contentCreator")
public class ContentCreator implements EntityCreator {
    @Override
    public BaseEntity createEntity() {
        return new ContentEntity();
    }
}
