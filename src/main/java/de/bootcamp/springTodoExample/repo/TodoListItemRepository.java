package de.bootcamp.springTodoExample.repo;

import de.bootcamp.springTodoExample.model.TodoListItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListItemRepository extends JpaRepository<TodoListItem, Integer> {
    List<TodoListItem> findAllByTodoListId(Integer todoListId);

    void deleteAllByTodoListId(Integer todoListId);
}
