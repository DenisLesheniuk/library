package ml.ledv.library.db.common.service.impl;

import ml.ledv.library.db.common.entity.BookEntity;
import ml.ledv.library.db.common.entity.UserEntity;
import ml.ledv.library.db.common.repository.BookRepository;
import ml.ledv.library.db.common.repository.UserRepository;
import ml.ledv.library.db.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BookRepository bookRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createUser(final String login) {

        final UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setBooks(new ArrayList<>());

        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(final UserEntity user) {

        for (BookEntity bookEntity : user.getBooks()) {
            bookEntity.setUser(null);
            bookRepository.save(bookEntity);
        }

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
}
