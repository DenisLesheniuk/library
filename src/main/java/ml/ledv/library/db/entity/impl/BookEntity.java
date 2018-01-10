package ml.ledv.library.db.entity.impl;

import ml.ledv.library.db.entity.BaseEntity;

import javax.persistence.*;

@Entity(name = "BOOK")
public class BookEntity extends BaseEntity {

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

    @ManyToOne
    @JoinColumn(name = "publisherId")
    private PublisherEntity publisher;

    public BookEntity() {
    }

    public BookEntity(final String id) {
        super(id);
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

    public PublisherEntity getPublisher() {

        return publisher;
    }

    public void setPublisher(final PublisherEntity publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author=" + author +
                ", content=" + content +
                ", publisher=" + publisher +
                '}';
    }
}
