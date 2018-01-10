package ml.ledv.library.db.entity.impl;

import ml.ledv.library.db.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "AUTHOR")
public class AuthorEntity extends BaseEntity {

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    public AuthorEntity() {
    }

    public AuthorEntity(final String id) {
        super(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
