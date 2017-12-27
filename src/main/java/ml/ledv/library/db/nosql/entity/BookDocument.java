package ml.ledv.library.db.nosql.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class BookDocument {

    @Id
    private String id;

    private String name;

    @DBRef(lazy = true)
    private UserDocument userDocument;

    public BookDocument() {
    }

    public BookDocument(final String id, final String name, final UserDocument userDocument) {
        this.id = id;
        this.name = name;
        this.userDocument = userDocument;
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

    public UserDocument getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(final UserDocument userDocument) {
        this.userDocument = userDocument;
    }

    @Override
    public String toString() {
        return "BookDocument{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userDocument=" + userDocument +
                '}';
    }
}
