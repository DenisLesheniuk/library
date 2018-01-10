package ml.ledv.library.cli.utils.task;

public interface Task {
    Task execute();

    void undo();
}
