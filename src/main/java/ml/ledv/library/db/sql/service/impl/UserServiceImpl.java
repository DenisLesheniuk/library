package ml.ledv.library.db.sql.service.impl;

import ml.ledv.library.db.nosql.entity.User;
import ml.ledv.library.db.nosql.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public void createUser(final String login) {

    }

    @Override
    public void deleteUser(final User user) {

    }

    @Override
    public Optional<User> getUserById(final String id) {
        return Optional.empty();
    }

    @Override
    public void updateUser(final User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
