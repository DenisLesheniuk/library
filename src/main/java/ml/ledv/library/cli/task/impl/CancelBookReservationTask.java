package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.task.Task;
import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.entity.impl.UserEntity;
import ml.ledv.library.db.service.BookService;
import ml.ledv.library.db.service.UserService;

import java.util.Optional;
import java.util.Scanner;

public class CancelBookReservationTask implements Task {

    private BookService bookService;
    private UserService userService;
    private Scanner scanner;

    private BookEntity bookEntity;
    private UserEntity userEntity;

    public CancelBookReservationTask(final BookService bookService, final UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Task execute() {

        String bookId = null;
        String userId = null;

        while (bookId == null) {
            System.out.println("Enter book id: ");
            bookId = scanner.nextLine();
        }

        final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + bookId + " is not exist!");
            return null;
        } else {

            bookEntity = bookOptional.get();

            while (userId == null) {
                System.out.println("Enter user id: ");
                userId = scanner.nextLine();
            }

            final Optional<UserEntity> userOptional = userService.getUserById(userId);
            if (!userOptional.isPresent()) {
                System.out.println("User with id " + userId + " is not exist!");
                return null;
            } else {

                userEntity = userOptional.get();

                userService.removeBook(userOptional.get(), bookOptional.get());
            }
        }
        System.out.println("BookDocument is returned - " + bookId);
        return this;
    }

    @Override
    public void undo() {
        System.out.println("Undo canceling book reservation - " + bookEntity.getName());

        userEntity.getBooks().add(bookEntity);
        userService.updateUser(userEntity);

        System.out.println(".... book " + bookEntity.getName() + " is added.");
    }
}
