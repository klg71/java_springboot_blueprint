package de.bootcamp.springTodoExample.repo;

import de.bootcamp.springTodoExample.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList,Integer> {
}
