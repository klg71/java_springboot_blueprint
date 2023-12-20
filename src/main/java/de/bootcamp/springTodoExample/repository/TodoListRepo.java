package de.bootcamp.springTodoExample.repository;

import de.bootcamp.springTodoExample.model.TodoEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepo extends JpaRepository<TodoEntry, UUID> {

}
