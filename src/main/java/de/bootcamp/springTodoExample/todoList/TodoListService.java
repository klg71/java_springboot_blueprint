package de.bootcamp.springTodoExample.todoList;

import de.bootcamp.springTodoExample.model.TodoEntry;
import de.bootcamp.springTodoExample.repository.TodoListRepo;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

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
