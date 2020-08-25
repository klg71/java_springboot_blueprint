package de.osp.springTodoExample.todoList;

import de.osp.springTodoExample.model.TodoList;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface TodoListRepo extends CrudRepository<TodoList, Long> {

  List<TodoList> findAll();
}
