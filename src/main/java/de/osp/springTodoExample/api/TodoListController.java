package de.osp.springTodoExample.api;

import de.osp.springTodoExample.model.Entry;
import de.osp.springTodoExample.model.EntryStatus;
import de.osp.springTodoExample.model.TodoList;
import de.osp.springTodoExample.service.TodoListService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping
    public List<TodoListView> all() {
        final var views = new ArrayList<TodoListView>();

        for(var todoList : todoListService.all()){
            var view = new TodoListView(todoList.getId(),todoList.getDescription(),
                    todoListService.getEntries(todoList.getId()));
            views.add(view);
        }
        return views;
    }

    @PostMapping("/create")
    public TodoList createTodoList(@RequestBody String description) {
        return todoListService.createTodoList(description);
    }

    @PostMapping("/{todoListId}/entry")
    public Entry addEntry(@PathVariable("todoListId") Integer todoListId, @RequestBody AddEntryRequest request) {
        return todoListService.addEntry(todoListId,
                request.getDueDate(), request.getDescription(),
                request.getPriority());
    }

    @PutMapping("/entry/{entryId}/updateStatus")
    public Entry updateStatus(@PathVariable("entryId")Integer entryId,
                              @RequestBody EntryStatus newStatus){
        return todoListService.updateStatus(entryId,newStatus);
    }

    @DeleteMapping("/entry/{entryId}/")
    public void deleteEntry(@PathVariable("entryId") Integer entryId){
        todoListService.deleteEntry(entryId);
    }
}
