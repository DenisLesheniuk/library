package ml.ledv.library.db.nosql.service.impl;

import ml.ledv.library.db.nosql.document.BookDocument;
import ml.ledv.library.db.nosql.document.UserDocument;
import ml.ledv.library.db.nosql.repository.BookRepository;
import ml.ledv.library.db.nosql.repository.UserRepository;
import ml.ledv.library.db.nosql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userServiceImplNoSQL")
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

        final UserDocument userDocument = new UserDocument();
        final List<BookDocument> bookDocuments = new ArrayList<>();

        userDocument.setLogin(login);
        userDocument.setBookDocuments(bookDocuments);

        userRepository.save(userDocument);
    }

    @Override
    public void deleteUser(final UserDocument userDocument) {

        for (BookDocument bookDocument : userDocument.getBookDocuments()) {
            bookDocument.setUserDocument(null);
            bookRepository.save(bookDocument);
        }
        userRepository.delete(userDocument);
    }

    @Override
    public Optional<UserDocument> getUserById(final String id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateUser(final UserDocument userDocument) {
        userRepository.save(userDocument);
    }

    @Override
    public List<UserDocument> getAll() {
        return userRepository.findAll();
    }


}
