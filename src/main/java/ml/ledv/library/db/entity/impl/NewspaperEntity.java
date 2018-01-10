package ml.ledv.library.db.entity.impl;

import ml.ledv.library.db.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "NEWSPAPER")
public class NewspaperEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "publish_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "publisherId")
    private PublisherEntity publisher;

    public NewspaperEntity() {
    }

    public NewspaperEntity(final String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(final PublisherEntity publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "NewspaperEntity{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", publisher=" + publisher +
                '}';
    }
}
