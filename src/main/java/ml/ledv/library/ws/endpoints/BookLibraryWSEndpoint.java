package ml.ledv.library.ws.endpoints;

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

@Endpoint
public class BookLibraryWSEndpoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private UserService userService;

    private BookService bookService;

    @Autowired
    public BookLibraryWSEndpoint(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsersRequest")
    @ResponsePayload
    public GetUsersResponse getUsers(@RequestPayload GetUsersRequest request) {

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
    public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {

        final CreateUserResponse response = new CreateUserResponse();
        final ServiceStatus status = new ServiceStatus();

        userService.createUser(request.getLogin());

        status.setStatusCode("SUCCESS");
        status.setMessage("Content Added Successfully");
        response.setServiceStatus(status);

        return response;
    }
}
