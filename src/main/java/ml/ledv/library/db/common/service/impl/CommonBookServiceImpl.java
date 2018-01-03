package ml.ledv.library.db.common.service.impl;


import ml.ledv.library.db.common.entity.CommonBookEntity;
import ml.ledv.library.db.common.entity.CommonUserEntity;
import ml.ledv.library.db.common.repository.jpa.JpaBookRepository;
import ml.ledv.library.db.common.repository.jpa.JpaUserRepository;
import ml.ledv.library.db.common.repository.mongo.MongoBookRepository;
import ml.ledv.library.db.common.service.CommonBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service()
public class CommonBookServiceImpl implements CommonBookService {

    private MongoBookRepository bookRepository;

    private JpaUserRepository userRepository;

    @Autowired
    public CommonBookServiceImpl(MongoBookRepository bookRepository, JpaUserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createBook(final String name) {
        final CommonBookEntity bookEntity = new CommonBookEntity();
        bookEntity.setName(name);

        bookRepository.save(bookEntity);
    }

    @Override
    public void deleteBook(final CommonBookEntity book) {
        bookRepository.delete(book);
    }

    @Override
    public List<CommonBookEntity> getAll() {
        return (List<CommonBookEntity>) bookRepository.findAll();
    }

    @Override
    public List<CommonBookEntity> getAllFree() {
        return bookRepository.getCommonBookEntitiesByUserIsNull();
    }

    @Override
    public Optional<CommonBookEntity> getBookById(final String id) {
        return bookRepository.findById(id);
    }

    @Override
    public void updateBook(final CommonBookEntity book) {
        bookRepository.save(book);
    }

    @Override
    public void removeUser(final CommonBookEntity book) {

        final CommonUserEntity userEntity = book.getUser();

        book.setUser(null);

        userEntity.getBooks().remove(book);

        userRepository.save(userEntity);
        bookRepository.save(book);
    }
}
