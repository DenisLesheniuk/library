package ml.ledv.library.db.service.impl;

import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.repository.BookRepository;
import ml.ledv.library.db.service.BookService;
import ml.ledv.library.db.utils.EntityCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private EntityCreator bookCreator;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository, final EntityCreator bookCreator) {
        this.bookRepository = bookRepository;
        this.bookCreator = bookCreator;
    }

    @Override
    public void createBook(final String name) {

        final BookEntity bookEntity = (BookEntity) bookCreator.createEntity();
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
    public Optional<BookEntity> getBookById(final String id) {
        return bookRepository.findById(id);
    }

    @Override
    public void updateBook(final BookEntity book) {
        bookRepository.save(book);
    }
}
