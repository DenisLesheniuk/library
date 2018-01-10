package ml.ledv.library.db.entity.impl;

import ml.ledv.library.db.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "PUBLISHER")
public class PublisherEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    public PublisherEntity() {
    }

    public PublisherEntity(final String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PublisherEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
