package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.utils.Printer;
import ml.ledv.library.cli.task.Task;
import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.service.BookService;

import java.util.List;

public class ShowAllBooksTask implements Task {

    private BookService bookService;
    private List<BookEntity> bookEntities;

    public ShowAllBooksTask(final BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Task execute() {

        bookEntities = bookService.getAll();

        for (BookEntity book : bookEntities) {
            Printer.printBooks(book);
        }
        return this;
    }

    @Override
    public void undo() {
        for (BookEntity book : bookEntities) {
            Printer.printBooks(book);
        }
    }
}
