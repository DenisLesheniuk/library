package ml.ledv.library.cli.task;

public interface Task {

    Task execute();

    void undo();
}
