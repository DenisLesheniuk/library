package ml.ledv.library.ws.endpoint;

import io.spring.guides.gs_producing_web_service.*;
import ml.ledv.library.db.sql.entity.impl.BookEntity;
import ml.ledv.library.db.sql.entity.impl.UserEntity;
import ml.ledv.library.db.sql.service.BookService;
import ml.ledv.library.db.sql.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Endpoint
public class BookLibraryWSEndpoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private UserService userService;

    private BookService bookService;

    @Autowired
    public BookLibraryWSEndpoint(final UserService userService, final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsersRequest")
    @ResponsePayload
    public GetUsersResponse getUsers(@RequestPayload final GetUsersRequest request) {

        final GetUsersResponse response = new GetUsersResponse();

        final List<UserEntity> userEntities = userService.getAll();
        final List<UserInfo> usersInfo = new ArrayList<>();

        for (UserEntity userEntity : userEntities) {
            final UserInfo userInfo = new UserInfo();
            if (userEntity.getBooks().size() > 0) {
                for (BookEntity bookEntity : userEntity.getBooks()) {

                    final BookInfo bookInfo = new BookInfo();
                    BeanUtils.copyProperties(bookEntity, bookInfo);

                    userInfo.getBooks().add(bookInfo);
                }
            }

            userInfo.setId(userEntity.getId());
            userInfo.setLogin(userEntity.getLogin());

            usersInfo.add(userInfo);
        }

        response.getUserEntities().addAll(usersInfo);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
    @ResponsePayload
    public CreateUserResponse createUser(@RequestPayload final CreateUserRequest request) {

        final CreateUserResponse response = new CreateUserResponse();
        final ServiceStatus status = new ServiceStatus();

        final String login = request.getLogin();

        if (login.length() == 0) {

            status.setStatusCode("BAD_REQUEST");
            status.setMessage("Empty tag 'login'. ");

            response.setServiceStatus(status);

            return response;
        }

        userService.createUser(request.getLogin());

        status.setStatusCode("SUCCESS");
        status.setMessage("User Added Successfully.");
        response.setServiceStatus(status);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteUser(@RequestPayload final DeleteUserRequest request) {

        final DeleteUserResponse response = new DeleteUserResponse();
        final ServiceStatus status = new ServiceStatus();

        final Optional<UserEntity> userOptional = userService.getUserById(request.getId());

        if (!userOptional.isPresent()) {

            status.setStatusCode("NOT_FOUND");
            status.setMessage("User with id " + request.getId() + " does not exist.");

            response.setServiceStatus(status);

            return response;
        } else {

            userService.deleteUser(userOptional.get());

            status.setStatusCode("DELETED");
            status.setMessage("User with id " + request.getId() + " deleted.");

            response.setServiceStatus(status);

            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBooksRequest")
    @ResponsePayload
    public GetBooksResponse getBooks(@RequestPayload final GetBooksRequest request) {

        final GetBooksResponse response = new GetBooksResponse();

        final List<BookEntity> bookEntities = bookService.getAll();
        final List<BookInfo> bookInfos = new ArrayList<>();

        for (BookEntity bookEntity : bookEntities) {

            final BookInfo bookInfo = new BookInfo();

            if (bookEntity.getUserEntity() != null) {

                final UserInfo userInfo = new UserInfo();

                BeanUtils.copyProperties(bookEntity.getUserEntity(), userInfo);
                bookInfo.setUserEntity(userInfo);
            }

            bookInfo.setId(bookEntity.getId());
            bookInfo.setName(bookEntity.getName());

            bookInfos.add(bookInfo);

        }

        response.getBooks().addAll(bookInfos);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createBookRequest")
    @ResponsePayload
    public CreateBookResponse createBook(@RequestPayload final CreateBookRequest request) {

        final CreateBookResponse response = new CreateBookResponse();
        final ServiceStatus status = new ServiceStatus();

        final String name = request.getName();

        if (name.length() == 0) {
            status.setStatusCode("BAD_REQUEST");
            status.setMessage("Empty tag 'name'. ");

            response.setServiceStatus(status);

            return response;
        } else {

            bookService.createBook(name);

            status.setStatusCode("SUCCESS");
            status.setMessage("Book added Successfully");

            response.setServiceStatus(status);

            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBookRequest")
    @ResponsePayload
    public DeleteBookResponse deleteBook(@RequestPayload final DeleteBookRequest request) {

        final DeleteBookResponse response = new DeleteBookResponse();
        final ServiceStatus status = new ServiceStatus();
        final String id = request.getId();

        final Optional<BookEntity> bookOptional = bookService.getBookById(id);

        if (!bookOptional.isPresent()) {

            status.setStatusCode("NOT_FOUND");
            status.setMessage("Book with id " + id + " does not exist.");

            response.setServiceStatus(status);

            return response;
        } else {

            bookService.deleteBook(bookOptional.get());

            status.setStatusCode("DELETED");
            status.setMessage("Deleted book with id " + id + ".");

            response.setServiceStatus(status);

            return response;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "reserveBookRequest")
    @ResponsePayload
    public ReserveBookResponse reserveBook(@RequestPayload final ReserveBookRequest request) {

        final ReserveBookResponse response = new ReserveBookResponse();
        final ServiceStatus status = new ServiceStatus();

        final String userId = request.getUserId();
        final String bookId = request.getBookId();

        final Optional<UserEntity> userOptional = userService.getUserById(userId);
        final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

        if (!userOptional.isPresent()) {

            status.setStatusCode("NOT_FOUND");
            status.setMessage("User with id " + userId + " does not exist.");

            response.setServiceStatus(status);

            return response;
        } else {

            if (!bookOptional.isPresent()) {

                status.setStatusCode("NOT_FOUND");
                status.setMessage("Book with id " + bookId + " does not exist.");

                response.setServiceStatus(status);

                return response;
            } else {

                final UserEntity userEntity = userOptional.get();
                final BookEntity bookEntity = bookOptional.get();

                if (bookEntity.getUserEntity() != null) {

                    status.setStatusCode("BAD_REQUEST");
                    status.setMessage("Book with id " + bookId + " already reserved");

                    response.setServiceStatus(status);

                    return response;
                } else {
                    bookEntity.setUserEntity(userEntity);
                    userEntity.getBooks().add(bookEntity);

                    userService.updateUser(userEntity);
                    bookService.updateBook(bookEntity);

                    status.setStatusCode("SUCCESS");
                    status.setMessage("Book with id " + bookId + " reserved by User with id " + userId);

                    response.setServiceStatus(status);

                    return response;
                }
            }
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "cancelBookReservationRequest")
    @ResponsePayload
    public CancelBookReservationResponse cancelBookReservation(@RequestPayload final CancelBookReservationRequest request) {

        final CancelBookReservationResponse response = new CancelBookReservationResponse();
        final ServiceStatus status = new ServiceStatus();
        final String id = request.getId();

        if (id.length() == 0) {
            status.setStatusCode("BAD_REQUEST");
            status.setMessage("Empty tag 'id'. ");

            response.setServiceStatus(status);

            return response;
        } else {
            final Optional<BookEntity> bookOptional = bookService.getBookById(id);

            if (!bookOptional.isPresent()) {
                status.setStatusCode("NOT_FOUND");
                status.setMessage("Book with id " + id + " does not exist.");

                response.setServiceStatus(status);

                return response;
            } else {

                final BookEntity bookEntity = bookOptional.get();
                final UserEntity userEntity = bookEntity.getUserEntity();

                userEntity.getBooks().remove(bookEntity);
                bookEntity.setUserEntity(null);

                userService.updateUser(userEntity);
                bookService.updateBook(bookEntity);

                status.setStatusCode("SUCCESS");
                status.setMessage("Canceled reservation for book with id " + id);

                response.setServiceStatus(status);

                return response;
            }
        }
    }
}
