package ml.ledv.library.db.common.entity;

import ml.ledv.library.db.common.entity.CommonBookEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Document(collection = "user")
public class CommonUserEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @org.springframework.data.annotation.Id
    private String id;

    private String login;

    @DBRef(lazy = true)
    @OneToMany(fetch = FetchType.EAGER)
    private List<CommonBookEntity> books;

    public CommonUserEntity() {
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

    public List<CommonBookEntity> getBooks() {
        return books;
    }

    public void setBooks(final List<CommonBookEntity> books) {
        this.books = books;
    }
}
