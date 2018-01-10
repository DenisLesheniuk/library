package ml.ledv.library.db.service.impl;

import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.entity.impl.UserEntity;
import ml.ledv.library.db.repository.UserRepository;
import ml.ledv.library.db.service.UserService;
import ml.ledv.library.db.utils.EntityCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private EntityCreator userCreator;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final EntityCreator userCreator) {
        this.userRepository = userRepository;
        this.userCreator = userCreator;
    }

    @Override
    public void createUser(final String login) {

        final UserEntity userEntity = (UserEntity) userCreator.createEntity();
        userEntity.setLogin(login);
        userEntity.setBooks(new ArrayList<>());

        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(final UserEntity user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<UserEntity> getUserById(final String id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateUser(final UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAll() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public void addBook(final UserEntity user, final BookEntity book) {
        user.getBooks().add(book);
        userRepository.save(user);
    }

    @Override
    public void removeBook(final UserEntity user, final BookEntity book) {
        user.getBooks().remove(book);
        userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> getUserByBook(final BookEntity bookEntity) {
        return userRepository.findByBooks(bookEntity);
    }
}
