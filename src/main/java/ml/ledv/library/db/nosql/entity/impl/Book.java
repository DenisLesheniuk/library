package ml.ledv.library.db.nosql.entity.impl;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Book {

    @Id
    private String id;

    private String name;

    @DBRef(lazy = true)
    private User user;

    public Book() {
    }

    public Book(String id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
