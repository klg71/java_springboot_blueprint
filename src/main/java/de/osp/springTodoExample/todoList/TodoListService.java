package de.osp.springTodoExample.todoList;

import de.osp.springTodoExample.model.TodoList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {

  private final TodoListRepo todoListRepo;

  TodoListService(
      final TodoListRepo todoListRepo
  ) {
    this.todoListRepo = todoListRepo;

    todoListRepo.save(new TodoList("a new list"));
    todoListRepo.save(new TodoList("a second list"));
    todoListRepo.save(new TodoList("a third list"));
  }

  public List<TodoList> getDummyTodoList() {
    return todoListRepo.findAll();
  }
}
