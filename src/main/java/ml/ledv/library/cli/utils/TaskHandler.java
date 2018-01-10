package ml.ledv.library.cli.utils;

import ml.ledv.library.cli.utils.task.Task;

import ml.ledv.library.cli.utils.task.impl.AddBookTask;
import ml.ledv.library.db.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.LinkedList;


@Service
public class TaskHandler {

    private Task task;
    private BookService bookService;

    private Deque<Task> stack;

    @Autowired
    public TaskHandler(final BookService bookService) {
        this.bookService = bookService;
        this.stack = new LinkedList<>();
    }

    public void addBook() {
        task = new AddBookTask(bookService);
        stack.push(task.execute());
    }

    public void undo(){
        if(!stack.isEmpty()){
            stack.pop().undo();
        }else {
            System.out.println("Can't undo!");
        }
    }
}
