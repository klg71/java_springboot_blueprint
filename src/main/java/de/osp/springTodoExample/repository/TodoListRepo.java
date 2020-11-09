package de.osp.springTodoExample.repository;

import de.osp.springTodoExample.model.TodoEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface TodoListRepo extends JpaRepository<TodoEntry, Long> {

}
