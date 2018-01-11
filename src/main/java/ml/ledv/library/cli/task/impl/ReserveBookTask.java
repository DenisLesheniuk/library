package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.task.Task;
import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.entity.impl.UserEntity;
import ml.ledv.library.db.service.BookService;
import ml.ledv.library.db.service.UserService;

import java.util.Optional;
import java.util.Scanner;

public class ReserveBookTask implements Task {

    private BookService bookService;
    private UserService userService;

    private Scanner scanner;

    private BookEntity bookEntity;
    private UserEntity userEntity;

    public ReserveBookTask(final BookService bookService, final UserService userService) {
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
        while (userId == null) {

            System.out.println("Enter user id: ");
            userId = scanner.nextLine();
        }

        final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + bookId + " is not exist! ");
            return null;
        } else {

            bookEntity = bookOptional.get();

            if (userService.getUserByBook(bookEntity).isPresent()) {
                System.out.println("Book " + bookEntity.getName() + " is already reserved.");
                return null;
            } else {

                final Optional<UserEntity> userOptional = userService.getUserById(userId);

                if (!userOptional.isPresent()) {
                    System.out.println("User with id " + userId + " is not exist! ");
                    return null;
                } else {

                    userEntity = userOptional.get();

                    userEntity.getBooks().add(bookEntity);
                    userService.updateUser(userEntity);
                }
            }
        }

        System.out.println("BookDocument " + bookId + " is reserved " + " by " + userId);

        return this;
    }

    @Override
    public void undo() {
        System.out.println("Undo reserving book - " + bookEntity.getName());

        userService.removeBook(userEntity, bookEntity);

        System.out.println(".... book " + bookEntity.getName() + " is unreserved!");
    }
}
