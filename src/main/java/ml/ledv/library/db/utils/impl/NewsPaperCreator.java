package ml.ledv.library.db.utils.impl;

import ml.ledv.library.db.entity.BaseEntity;
import ml.ledv.library.db.entity.impl.NewsPaperEntity;
import ml.ledv.library.db.utils.EntityCreator;
import org.springframework.stereotype.Service;

@Service("newsPaperCreator")
public class NewsPaperCreator implements EntityCreator {
    @Override
    public BaseEntity createEntity() {
        return new NewsPaperEntity();
    }
}
