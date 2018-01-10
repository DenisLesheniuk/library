package ml.ledv.library.db.utils.impl;

import ml.ledv.library.db.entity.content.LibraryContent;
import ml.ledv.library.db.entity.content.NewsPaperEntity;
import ml.ledv.library.db.utils.LibraryContentCreator;
import org.springframework.stereotype.Service;

@Service("newsPaperCreator")
public class NewsPaperCreator implements LibraryContentCreator {
    @Override
    public LibraryContent createContent() {
        return new NewsPaperEntity();
    }
}
