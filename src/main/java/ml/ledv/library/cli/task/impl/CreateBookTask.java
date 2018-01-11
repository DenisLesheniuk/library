package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.task.Task;
import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class CreateBookTask implements Task {

    private BookService bookService;
    private Scanner scanner;

    private BookEntity bookEntity;

    @Autowired
    public CreateBookTask(final BookService bookService) {
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Task execute() {
        String bookName = null;

        while (bookName == null) {
            System.out.println("\nEnter book name: ");
            bookName = scanner.nextLine();
        }

        bookEntity = bookService.createBook(bookName);

        System.out.println("Created book with name - " + bookName + "\n");

        return this;
    }

    @Override
    public void undo() {
        System.out.println("\nUndo creating book with name  - " + bookEntity.getName());

        bookService.deleteBook(bookEntity);

        System.out.println(".... book with name " + bookEntity.getName() + " is deleted.");
    }
}
