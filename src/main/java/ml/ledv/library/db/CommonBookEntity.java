package ml.ledv.library.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;

@Entity(name = "book")
public class CommonBookEntity {

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
    private CommonUserEntity user;

    public CommonBookEntity() {
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

    public CommonUserEntity getUser() {
        return user;
    }

    public void setUser(final CommonUserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CommonBookEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
