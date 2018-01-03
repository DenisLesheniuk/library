package ml.ledv.library.db.common.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Entity(name = "book")
@Document(collection = "book")
public class BookEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @org.springframework.data.annotation.Id
    private String id;

    private String name;

    @DBRef(lazy = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    public BookEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(final UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
