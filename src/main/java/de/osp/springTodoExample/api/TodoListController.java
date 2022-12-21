package de.osp.springTodoExample.api;

import de.osp.springTodoExample.model.Task;
import de.osp.springTodoExample.service.TodoListService;
import de.osp.springTodoExample.view.TaskView;
import de.osp.springTodoExample.view.TodoListView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todoList")
public class TodoListController {

    private final TodoListService todoListService;

    TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping()
    public List<TodoListView> getAllTodoLists() {
        return todoListService.getAllTodoLists();
    }

    @PostMapping
    public TodoListView addTodoList(@RequestBody TodoListView todoList) {
        return todoListService.createTodoList(todoList);
    }

    @GetMapping("/{id}")
    public TodoListView getTodoListById(@PathVariable("id") Integer todoListId) {
        return todoListService.getTodoListById(todoListId);
    }

    @PutMapping()
    public TodoListView updateTodoList(TodoListView todoListView) {
        return todoListService.updateToDoList(todoListView);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoList(@PathVariable("id") Integer todoListId) {
        todoListService.deleteTodoList(todoListId);
    }

    @PutMapping("/{id}/task/edit/{taskId}")
    public TaskView updateTask(@PathVariable("id") Integer todoListId, @PathVariable("taskId") Integer taskId, @RequestBody TaskView taskView) {
        return todoListService.updateTaskView(todoListId, taskId, taskView);
    }

    @PostMapping("/{id}/task/create")
    public TaskView createTask(@PathVariable("id") Integer todoListId, @RequestBody TaskView taskView) {
        return todoListService.createTaskForList(todoListId, taskView);
    }

    @GetMapping("/{id}/task")
    public List<Task> getTasksForTodoList(@PathVariable("id") Integer todoListId) {
        return todoListService.getTasksForTodoList(todoListId);
    }
}
