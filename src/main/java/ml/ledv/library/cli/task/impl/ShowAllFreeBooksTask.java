package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.task.Task;
import ml.ledv.library.cli.utils.Printer;
import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.entity.impl.UserEntity;
import ml.ledv.library.db.service.BookService;
import ml.ledv.library.db.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowAllFreeBooksTask implements Task {

    private BookService bookService;
    private UserService userService;

    private List<BookEntity> freeBookEntities;

    public ShowAllFreeBooksTask(final BookService bookService, final UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
        this.freeBookEntities = new ArrayList<>();
    }

    @Override
    public Task execute() {

        final List<BookEntity> bookEntities = bookService.getAll();
        Optional<UserEntity> userOptional = null;

        for (BookEntity book : bookEntities) {
            userOptional = userService.getUserByBook(book);
            if (!userOptional.isPresent()) {
                Printer.printBooks(book);
                freeBookEntities.add(book);
            }
        }

        return this;
    }

    @Override
    public void undo() {
        for (BookEntity book : freeBookEntities) {
            Printer.printBooks(book);
        }
    }
}
