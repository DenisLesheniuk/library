package ml.ledv.library.db.sql.service.impl;


import ml.ledv.library.db.sql.entity.impl.BookEntity;
import ml.ledv.library.db.sql.entity.impl.UserEntity;
import ml.ledv.library.db.sql.repository.BookRepositorySQL;
import ml.ledv.library.db.sql.repository.UserRepositorySQL;
import ml.ledv.library.db.sql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("bookServiceImplSQL")
public class BookServiceImpl implements BookService {

    private BookRepositorySQL bookRepository;

    private UserRepositorySQL userRepository;

    @Autowired
    public BookServiceImpl(BookRepositorySQL bookRepository, UserRepositorySQL userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createBook(final String name) {
        final BookEntity bookEntity = new BookEntity();
        bookEntity.setName(name);

        bookRepository.save(bookEntity);
    }

    @Override
    public void deleteBook(final BookEntity book) {
        bookRepository.delete(book);
    }

    @Override
    public List<BookEntity> getAll() {
        return (List<BookEntity>) bookRepository.findAll();
    }

    @Override
    public List<BookEntity> getAllFree() {
        return bookRepository.getBookEntitiesByUserEntityIsNull();
    }

    @Override
    public Optional<BookEntity> getBookById(final String id) {
        return bookRepository.findById(id);
    }

    @Override
    public void updateBook(final BookEntity book) {
        bookRepository.save(book);
    }

    @Override
    public void removeUser(final BookEntity book) {

        final UserEntity userEntity = book.getUserEntity();

        book.setUserEntity(null);

        userEntity.getBooks().remove(book);

        userRepository.save(userEntity);
        bookRepository.save(book);
    }
}
