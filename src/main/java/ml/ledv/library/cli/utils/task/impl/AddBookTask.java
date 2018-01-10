package ml.ledv.library.cli.utils.task.impl;

import ml.ledv.library.cli.utils.task.Task;
import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class AddBookTask implements Task {

    private BookService bookService;
    private Scanner scanner;

    private BookEntity bookEntity;

    @Autowired
    public AddBookTask(final BookService bookService) {
        this.bookService = bookService;
        scanner = new Scanner(System.in);
    }

    @Override
    public Task execute() {
        String bookName = null;

        while (bookName == null) {
            System.out.println("Enter book name: ");
            bookName = scanner.nextLine();
        }

        bookEntity = bookService.createBook(bookName);

        System.out.println("Created book - " + bookName);

        return this;
    }

    @Override
    public void undo() {
        System.out.println("Undo creating the book - " + bookEntity.getName());
        bookService.deleteBook(bookEntity);
        System.out.println(".... book " + bookEntity.getName() + " is deleted.");
    }
}
