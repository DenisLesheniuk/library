package ml.ledv.library.cli.impl;

import ml.ledv.library.cli.CLI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CLIImpl implements CLI {

    private TaskHandler taskHandler;

    @Autowired
    public CLIImpl(final TaskHandler taskHandler) {
        this.taskHandler = taskHandler;
    }

    @Override
    public void start() {
            taskHandler.showLibraryMenu();
    }
}
