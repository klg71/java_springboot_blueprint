package de.osp.springTodoExample.todoList;

import de.osp.springTodoExample.model.TodoEntry;
import de.osp.springTodoExample.repository.TodoListRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class TodoListService {

    private final TodoListRepo todoListRepo;

    TodoListService(
            final TodoListRepo todoListRepo
    ) {
        this.todoListRepo = todoListRepo;
    }

    public List<TodoEntry> get() {
        return todoListRepo.findAll();
    }

    public void add(String todo) {
        final var entry = new TodoEntry();
        entry.setTitle(todo);
        todoListRepo.save(entry);
    }

    public void delete(UUID id) {
        todoListRepo.deleteById(id);
    }
}
