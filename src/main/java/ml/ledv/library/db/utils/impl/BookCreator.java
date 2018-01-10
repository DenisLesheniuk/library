package ml.ledv.library.db.utils.impl;

import ml.ledv.library.db.entity.content.BookEntity;
import ml.ledv.library.db.entity.content.LibraryContent;
import ml.ledv.library.db.utils.LibraryContentCreator;
import org.springframework.stereotype.Service;

@Service("bookCreator")
public class BookCreator implements LibraryContentCreator {

    @Override
    public LibraryContent createContent() {
        return new BookEntity();
    }
}
