package ml.ledv.library.db.common.service.impl;

import ml.ledv.library.db.common.entity.BookEntity;
import ml.ledv.library.db.common.entity.UserEntity;
import ml.ledv.library.db.common.repository.BookRepository;
import ml.ledv.library.db.common.repository.UserRepository;
import ml.ledv.library.db.common.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service()
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private UserRepository userRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
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
        return bookRepository.getCommonBookEntitiesByUserIsNull();
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

        final UserEntity userEntity = book.getUser();

        book.setUser(null);

        userEntity.getBooks().remove(book);

        userRepository.save(userEntity);
        bookRepository.save(book);
    }
}
