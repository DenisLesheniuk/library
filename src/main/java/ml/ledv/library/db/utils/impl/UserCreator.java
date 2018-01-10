package ml.ledv.library.db.utils.impl;

import ml.ledv.library.db.entity.BaseEntity;
import ml.ledv.library.db.entity.impl.UserEntity;
import ml.ledv.library.db.utils.EntityCreator;
import org.springframework.stereotype.Service;

@Service("userCreator")
public class UserCreator implements EntityCreator {

    @Override
    public BaseEntity createEntity() {
        return new UserEntity();
    }
}
