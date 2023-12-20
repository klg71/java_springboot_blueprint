package de.bootcamp.springTodoExample.web;

import de.bootcamp.springTodoExample.model.TodoEntry;
import de.bootcamp.springTodoExample.todoList.TodoListService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(final TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping
    public List<TodoEntry> get() {
        return todoListService.get();
    }

    @PostMapping
    public void add(@RequestBody String todo) {
        todoListService.add(todo);
    }

    @DeleteMapping("/{id}")
    public void add(@PathVariable(name = "id") UUID id) {
        todoListService.delete(id);
    }
}
