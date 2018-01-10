package ml.ledv.library.db.entity.content;

import ml.ledv.library.db.entity.AuthorEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "BOOK")
public class BookEntity extends LibraryContent {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "isbn", unique = true)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private AuthorEntity author;

    @ManyToOne
    @JoinColumn(name = "contentId")
    private ContentEntity content;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(final AuthorEntity author) {
        this.author = author;
    }

    public ContentEntity getContent() {
        return content;
    }

    public void setContent(final ContentEntity content) {
        this.content = content;
    }
}
