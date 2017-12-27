package ml.ledv.library.cli.service;

public interface BookLibraryService {

    void createBook(String name);

    void deleteBook(String id);

    void reserveBook(String bookId, String userId);

    void showBooks();

    void showFreeBook();

    void createUser(String login);

    void deleteUser(String id);

    void showUsers();

    void cancelReservation(String id);
}
