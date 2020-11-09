package de.osp.springTodoExample.web;

import de.osp.springTodoExample.model.TodoEntry;
import de.osp.springTodoExample.todoList.TodoListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
