package ml.ledv.library.db.sql.service.impl;

import ml.ledv.library.db.nosql.entity.Book;
import ml.ledv.library.db.nosql.service.BookService;
import ml.ledv.library.db.sql.entity.impl.BookEntity;
import ml.ledv.library.db.sql.repository.BookRepository;
import ml.ledv.library.db.sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private UserRepository userRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createBook(final String login) {

        final BookEntity bookEntity = new BookEntity();
        bookEntity.setName(login);

    }

    @Override
    public void deleteBook(final Book book) {
        bookRepository.delete(book);
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public List<Book> getAllFree() {
        return null;
    }

    @Override
    public Optional<Book> getBookById(final String id) {
        return Optional.empty();
    }

    @Override
    public void updateBook(final Book book) {

    }

    @Override
    public void removeUser(final Book book) {

    }
}
