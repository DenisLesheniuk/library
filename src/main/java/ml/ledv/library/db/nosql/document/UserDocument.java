package ml.ledv.library.db.nosql.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class UserDocument {

    @Id
    private String id;

    @Indexed(unique = true)
    private String login;

    @DBRef(lazy = true)
    private List<BookDocument> bookDocuments;

    public UserDocument() {
    }

    public UserDocument(final String login, final List<BookDocument> bookDocuments) {
        this.login = login;
        this.bookDocuments = bookDocuments;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public List<BookDocument> getBookDocuments() {
        return bookDocuments;
    }

    public void setBookDocuments(final List<BookDocument> bookDocuments) {
        this.bookDocuments = bookDocuments;
    }

    @Override
    public String toString() {
        return "UserDocument{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", bookDocuments=" + bookDocuments +
                '}';
    }
}
