package ml.ledv.library.db.sql.entity.impl;

import ml.ledv.library.db.sql.entity.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "BOOK")
public class BookEntity extends BaseEntity {

    private String name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private UserEntity userEntity;

    public BookEntity() {
    }

    public BookEntity(final String id, final String name, final UserEntity userEntity) {
        super(id);
        this.name = name;
        this.userEntity = userEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "name='" + name + '\'' +
                ", userEntity=" + userEntity +
                '}';
    }
}
