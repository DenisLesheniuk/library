package ml.ledv.library.db.nosql.service.impl;

import ml.ledv.library.db.nosql.entity.BookDocument;
import ml.ledv.library.db.nosql.entity.UserDocument;
import ml.ledv.library.db.nosql.repository.BookRepository;
import ml.ledv.library.db.nosql.repository.UserRepository;
import ml.ledv.library.db.nosql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("bookServiceImplNoSQL")
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

        final BookDocument bookDocument = new BookDocument();
        bookDocument.setName(name);

        bookRepository.save(bookDocument);
    }

    @Override
    public void deleteBook(final BookDocument bookDocument) {

        if (bookDocument.getUserDocument() != null) {
            final UserDocument userDocument = bookDocument.getUserDocument();
            userDocument.getBookDocuments().remove(bookDocument);
            userRepository.save(userDocument);
        }

        bookRepository.delete(bookDocument);
    }

    @Override
    public List<BookDocument> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookDocument> getAllFree() {
        return bookRepository.getBooksByUserDocumentIsNull();
    }

    @Override
    public Optional<BookDocument> getBookById(final String id) {
        return bookRepository.findById(id);
    }

    @Override
    public void updateBook(final BookDocument bookDocument) {
        bookRepository.save(bookDocument);
    }

    @Override
    public void removeUser(final BookDocument bookDocument) {

        final UserDocument userDocument = bookDocument.getUserDocument();
        userDocument.getBookDocuments().remove(bookDocument);

        bookDocument.setUserDocument(null);

        bookRepository.save(bookDocument);
        userRepository.save(userDocument);
    }
}
