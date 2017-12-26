package ml.ledv.library.db.sql.entity.impl;

import ml.ledv.library.db.sql.entity.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "USER")
public class UserEntity extends BaseEntity {

    private String login;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<BookEntity> books;

    public UserEntity() {
    }

    public UserEntity(final String id, final String login, final List<BookEntity> books) {
        super(id);
        this.login = login;
        this.books = books;
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "login='" + login + '\'' +
                ", books=" + books +
                '}';
    }
}
