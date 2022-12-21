package de.osp.springTodoExample.service;

import de.osp.springTodoExample.model.Task;
import de.osp.springTodoExample.model.TodoList;
import de.osp.springTodoExample.repo.TaskRepository;
import de.osp.springTodoExample.repo.TodoListRepository;
import de.osp.springTodoExample.view.TaskView;
import de.osp.springTodoExample.view.TodoListView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final TaskRepository taskRepository;

    TodoListService(TodoListRepository todoListRepository, TaskRepository taskRepository) {
        this.todoListRepository = todoListRepository;
        this.taskRepository = taskRepository;
    }

    public TaskView createTaskForList(Integer todoListId, TaskView taskView) {
        Optional<TodoList> todoList = todoListRepository.findById(todoListId);
        todoList.ifPresent(it -> taskRepository.save(new Task(it.getId(), taskView.getTitle(), taskView.getDescription(), taskView.getDone(), taskView.getPriority())));
        return taskView;
    }

    public TaskView updateTaskView(Integer todoListId, Integer taskId, TaskView taskView) {
        Optional<TodoList> todoList = todoListRepository.findById(todoListId);
        if(todoList.isPresent()) {
            Optional<Task> task = taskRepository.findById(taskId);
            task.ifPresent(it -> taskRepository.deleteById(it.getId()));
            taskRepository.save(new Task(todoListId, taskView.getTitle(), taskView.getDescription(), taskView.getDone(), taskView.getPriority()));
        }else {
            throw new EntityNotFoundException();
        }
        return taskView;
    }

    public List<Task> getTasksForTodoList(Integer todoListId) {
        return taskRepository.findAllByListId(todoListId);
    }

    @Transactional(readOnly = true)
    public TodoListView getTodoListById(Integer todoListId) throws EntityNotFoundException {
        TodoList todoList = todoListRepository.getReferenceById(todoListId);
        List<Task> tasks = taskRepository.findAllByListId(todoListId);
        return new TodoListView(todoList.getId(), todoList.getTitle(), todoList.getDescription(), todoList.getDueDate(), mapToViews(tasks));
    }
    @Transactional
    public TodoListView updateToDoList(TodoListView todoListView) {
        Optional<TodoList> todoList = todoListRepository.findById(todoListView.getId());
        todoList.ifPresent(list -> deleteTodoList(list.getId()));
        return createTodoList(todoListView);
    }

    @Transactional
    public void deleteTodoList(Integer todoListId) {
        taskRepository.deleteAllByListId(todoListId);
        todoListRepository.deleteById(todoListId);
    }

    @Transactional(readOnly = true)
    public List<TodoListView> getAllTodoLists() {
        List<TodoList> todoLists = todoListRepository.findAll();
        List<TodoListView> views = new ArrayList<>();
        for (TodoList todoList : todoLists) {
            List<Task> tasks = taskRepository.findAllByListId(todoList.getId());
            views.add(new TodoListView(todoList.getId(), todoList.getTitle(), todoList.getDescription(), todoList.getDueDate(), mapToViews(tasks)));
        }
        return views;
    }

    @Transactional
    public TodoListView createTodoList(TodoListView todoListView) {
        TodoList todoList = new TodoList(todoListView.getId(), todoListView.getTitle(), todoListView.getDescription(), todoListView.getDueDate());
        TodoList savedTodoList = todoListRepository.save(todoList);

        for (TaskView task : todoListView.getTasks()) {
            taskRepository.save(new Task(savedTodoList.getId(), task.getTitle(), task.getDescription(), task.getDone(), task.getPriority()));
        }
        return todoListView;
    }

    private List<TaskView> mapToViews(List<Task> tasks) {
        return tasks.stream().map(it -> new TaskView(it.getTitle(), it.getDescription(), it.getIsDone(), it.getPriority())).toList();
    }
}
