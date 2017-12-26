package ml.ledv.library.db.nosql.service.impl;

import ml.ledv.library.db.nosql.entity.Book;
import ml.ledv.library.db.nosql.entity.User;
import ml.ledv.library.db.nosql.repository.BookRepository;
import ml.ledv.library.db.nosql.repository.UserRepository;
import ml.ledv.library.db.nosql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private UserRepository userRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository, final UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createBook(final String name) {

        final Book book = new Book();
        book.setName(name);

        bookRepository.save(book);
    }

    @Override
    public void deleteBook(final Book book) {

        if (book.getUser() != null) {
            final User user = book.getUser();
            user.getBooks().remove(book);
            userRepository.save(user);
        }


        bookRepository.delete(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getAllFree() {
        return bookRepository.getBooksByUserIsNull();
    }

    @Override
    public Optional<Book> getBookById(final String id) {
        return bookRepository.findById(id);
    }

    @Override
    public void updateBook(final Book book) {
        bookRepository.save(book);
    }

    @Override
    public void removeUser(final Book book) {

        final User user = book.getUser();
        user.getBooks().remove(book);

        book.setUser(null);

        bookRepository.save(book);
        userRepository.save(user);
    }
}
