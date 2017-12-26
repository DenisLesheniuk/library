package ml.ledv.library.db.nosql.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String login;

    @DBRef(lazy = true)
    private List<Book> books;

    public User() {
    }

    public User(final String login, final List<Book> books) {
        this.login = login;
        this.books = books;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", books=" + books +
                '}';
    }
}
