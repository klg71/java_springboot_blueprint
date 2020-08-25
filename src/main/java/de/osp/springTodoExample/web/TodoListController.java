package de.osp.springTodoExample.web;

import de.osp.springTodoExample.model.TodoList;
import de.osp.springTodoExample.todoList.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/todos")
public class TodoListController {

  @Autowired
  private TodoListService todoListService;

  @GetMapping
  public List<TodoList> get() {
    return todoListService.getDummyTodoList();
  }
}
