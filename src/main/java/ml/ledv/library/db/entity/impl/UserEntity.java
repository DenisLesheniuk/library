package ml.ledv.library.db.entity.impl;

import ml.ledv.library.db.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "USER")
public class UserEntity extends BaseEntity {

    @Column(name = "name")
    private String login;

    @OneToMany(fetch = FetchType.LAZY)
    private List<BookEntity> books;

    public UserEntity() {
    }

    public UserEntity(final String id) {
        super(id);
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
