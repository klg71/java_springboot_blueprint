package de.osp.springTodoExample.api;

import de.osp.springTodoExample.model.Entry;
import de.osp.springTodoExample.model.TodoList;
import de.osp.springTodoExample.service.TodoListService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todoList")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping
    public TodoList create(@RequestBody String name) {
        return todoListService.createTodoList(name);
    }

    @PostMapping("/{id}/entry")
    public Entry createEntry(@PathVariable("id") Integer todoListId, @RequestBody String text) {
        return todoListService.addEntry(todoListId, text);
    }

    @PutMapping("/entry/{entryId}/done")
    public Entry setEntryDone(@PathVariable("entryId") Integer entryId) {
        return todoListService.setEntryDone(entryId);
    }

    @GetMapping
    public List<TodoListView> all() {
        final var allLists = todoListService.all();

        final var views = new ArrayList<TodoListView>();
        for (final var list : allLists) {
            final var view = new TodoListView(list.getId(), list.getName(),
                    todoListService.getEntries(list.getId()));
            views.add(view);
        }

        return views;
    }
}
