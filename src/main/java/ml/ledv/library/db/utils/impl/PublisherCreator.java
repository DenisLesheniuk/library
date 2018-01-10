package ml.ledv.library.db.utils.impl;

import ml.ledv.library.db.entity.BaseEntity;
import ml.ledv.library.db.entity.PublisherEntity;
import ml.ledv.library.db.utils.EntityCreator;
import org.springframework.stereotype.Service;

@Service("publisherCreator")
public class PublisherCreator implements EntityCreator {
    @Override
    public BaseEntity createEntity() {
        return new PublisherEntity();
    }
}
