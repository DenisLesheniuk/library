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

            System.out.println("\nEnter book id: ");
            bookId = scanner.nextLine();
        }
        while (userId == null) {

            System.out.println("\nEnter user id: ");
            userId = scanner.nextLine();
        }

        final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            System.out.println("\nBook with id " + bookId + " is not exist! \n");
            return null;
        } else {

            bookEntity = bookOptional.get();

            if (userService.getUserByBook(bookEntity).isPresent()) {
                System.out.println("\nBook " + bookEntity.getName() + " is already reserved.\n");
                return null;
            } else {

                final Optional<UserEntity> userOptional = userService.getUserById(userId);

                if (!userOptional.isPresent()) {
                    System.out.println("\nUser with id " + userId + " is not exist!\n ");
                    return null;
                } else {

                    userEntity = userOptional.get();

                    userEntity.getBooks().add(bookEntity);
                    userService.updateUser(userEntity);
                }
            }
        }

        System.out.println("BookDocument " + bookId + " is reserved " + " by " + userId + "\n");

        return this;
    }

    @Override
    public void undo() {
        System.out.println("\nUndo reserving book - " + bookEntity.getName());

        userService.removeBook(userEntity, bookEntity);

        System.out.println(".... book " + bookEntity.getName() + " is unreserved!");
    }
}
