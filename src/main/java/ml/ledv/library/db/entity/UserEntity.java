package ml.ledv.library.db.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String login;

    @OneToMany(fetch = FetchType.EAGER)
    private List<BookEntity> books;

    public UserEntity() {
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

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(final List<BookEntity> books) {
        this.books = books;
    }
}
