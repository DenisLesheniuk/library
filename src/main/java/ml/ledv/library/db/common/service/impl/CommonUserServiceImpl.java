package ml.ledv.library.db.common.service.impl;

import ml.ledv.library.db.common.entity.CommonBookEntity;
import ml.ledv.library.db.common.entity.CommonUserEntity;
import ml.ledv.library.db.common.repository.jpa.JpaUserRepository;
import ml.ledv.library.db.common.repository.mongo.MongoBookRepository;
import ml.ledv.library.db.common.service.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service()
public class CommonUserServiceImpl implements CommonUserService {

    private JpaUserRepository userRepository;

    private MongoBookRepository bookRepository;

    @Autowired
    public CommonUserServiceImpl(final JpaUserRepository userRepository, final MongoBookRepository bookRepository) {
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
