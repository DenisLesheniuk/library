package ml.ledv.library.db.nosql.service.impl;

import ml.ledv.library.db.nosql.entity.Book;
import ml.ledv.library.db.nosql.entity.User;
import ml.ledv.library.db.nosql.repository.BookRepository;
import ml.ledv.library.db.nosql.repository.UserRepository;
import ml.ledv.library.db.nosql.service.UserService;
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

        final User user = new User();
        final List<Book> books = new ArrayList<>();

        user.setLogin(login);
        user.setBooks(books);

        userRepository.save(user);
    }

    @Override
    public void deleteUser(final User user) {

        for (Book book : user.getBooks()) {
            book.setUser(null);
            bookRepository.save(book);
        }
        userRepository.delete(user);
    }

    @Override
    public Optional<User> getUserById(final String id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateUser(final User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


}
