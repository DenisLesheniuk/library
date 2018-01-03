package ml.ledv.library.db.sql.service.impl;

import ml.ledv.library.db.CommonBookEntity;
import ml.ledv.library.db.CommonUserEntity;
import ml.ledv.library.db.repository.CommonBookRepository;
import ml.ledv.library.db.repository.CommonUserRepository;
import ml.ledv.library.db.sql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userServiceImplSQL")
public class UserServiceImpl implements UserService {

    private CommonUserRepository userRepository;

    private CommonBookRepository bookRepository;

    @Autowired
    public UserServiceImpl(final CommonUserRepository userRepository, final CommonBookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createUser(final String login) {

        final CommonUserEntity userEntity = new CommonUserEntity();
        userEntity.setLogin(login);
        userEntity.setBooks(new ArrayList<>());

        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(final CommonUserEntity user) {

        for (CommonBookEntity bookEntity: user.getBooks()){
            bookEntity.setUser(null);
            bookRepository.save(bookEntity);
        }

        userRepository.delete(user);
    }

    @Override
    public Optional<CommonUserEntity> getUserById(final String id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateUser(final CommonUserEntity user) {
        userRepository.save(user);
    }

    @Override
    public List<CommonUserEntity> getAll() {
        return (List<CommonUserEntity>) userRepository.findAll();
    }
}
