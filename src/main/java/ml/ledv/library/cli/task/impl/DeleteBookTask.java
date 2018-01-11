package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.task.Task;
import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.service.BookService;

import java.util.Optional;
import java.util.Scanner;

public class DeleteBookTask implements Task {

    private BookService bookService;
    private Scanner scanner;

    private BookEntity bookEntity;

    public DeleteBookTask(final BookService bookService) {
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Task execute() {

        String bookId = null;

        while (bookId == null) {
            System.out.println("Enter book name: ");
            bookId = scanner.nextLine();
        }

        final Optional<BookEntity> optionalBookEntity = bookService.getBookById(bookId);

        if (!optionalBookEntity.isPresent()) {
            System.out.println("Book with id " + bookId + " is not exist!");
            return null;
        } else {
            bookEntity = optionalBookEntity.get();
            bookService.deleteBook(bookEntity);
        }

        System.out.println("Deleted book - " + bookId);
        return this;
    }

    @Override
    public void undo() {
        System.out.println("Undo deleting the book - " + bookEntity.getName());

        final BookEntity book = bookService.saveBook(bookEntity);
        bookService.updateId(bookEntity.getId(), book.getId());

        System.out.println(".... book " + bookEntity.getName() + " is restored!");
    }
}
