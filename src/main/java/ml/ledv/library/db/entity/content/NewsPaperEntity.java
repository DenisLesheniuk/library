package ml.ledv.library.db.entity.content;

import ml.ledv.library.db.entity.PublisherEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "NEWSPAPER")
public class NewsPaperEntity extends LibraryContent {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "publish_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "publisherId")
    private PublisherEntity publisher;

    public NewsPaperEntity() {
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
}
