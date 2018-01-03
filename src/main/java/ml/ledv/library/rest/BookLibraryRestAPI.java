package ml.ledv.library.rest;

import ml.ledv.library.db.common.entity.CommonBookEntity;
import ml.ledv.library.db.common.entity.CommonUserEntity;
import ml.ledv.library.db.common.service.CommonBookService;
import ml.ledv.library.db.common.service.CommonUserService;
import ml.ledv.library.rest.params.BookInfo;
import ml.ledv.library.rest.params.UserParams;
import ml.ledv.library.rest.responce.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class BookLibraryRestAPI {

    private CommonUserService userService;

    private CommonBookService bookService;

    @Autowired
    public BookLibraryRestAPI(final CommonUserService userService, final CommonBookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody final UserParams userParams) {

        final String login = userParams.getLogin();

        if (login == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Empty field login"));
        } else {
            userService.createUser(login);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable final String id) {

        final Optional<CommonUserEntity> userOptional = userService.getUserById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            userService.deleteUser(userOptional.get());
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> reserveBook(@PathVariable final String id, @RequestBody final BookInfo bookParams) {

        final Optional<CommonUserEntity> userOptional = userService.getUserById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Not found user with id. " + id));
        } else {

            final String bookId = bookParams.getId();

            if (bookId == null) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Empty id field. "));
            } else {

                final Optional<CommonBookEntity> bookOptional = bookService.getBookById(bookId);

                if (!bookOptional.isPresent()) {
                    return ResponseEntity.badRequest().body(new ErrorResponse("Not found book with id. " + bookId));
                } else {

                    final CommonUserEntity userEntity = userOptional.get();
                    final CommonBookEntity bookEntity = bookOptional.get();

                    userEntity.getBooks().add(bookEntity);
                    bookEntity.setUser(userEntity);

                    bookService.updateBook(bookEntity);
                    userService.updateUser(userEntity);

                    return ResponseEntity.ok().build();
                }
            }
        }
    }

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody final BookInfo bookParams) {

        final String name = bookParams.getName();

        if (name == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Empty field name"));
        } else {
            bookService.createBook(name);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @GetMapping("/books")
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable final String id) {

        final Optional<CommonBookEntity> bookOptional = bookService.getBookById(id);

        if (!bookOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            bookService.deleteBook(bookOptional.get());
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<?> cancelBookReservation(@PathVariable final String id) {

        final Optional<CommonBookEntity> bookOptional = bookService.getBookById(id);

        if (!bookOptional.isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Not found book with id. " + id));
        } else {

            final CommonBookEntity bookEntity = bookOptional.get();
            final CommonUserEntity userEntity = bookEntity.getUser();

            bookEntity.setUser(null);

            userEntity.getBooks().remove(bookEntity);

            bookService.updateBook(bookEntity);
            userService.updateUser(userEntity);

            return ResponseEntity.ok().build();
        }
    }
}