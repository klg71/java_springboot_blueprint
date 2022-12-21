package de.osp.springTodoExample.repo;

import de.osp.springTodoExample.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByListId(Integer listId);

    void deleteAllByListId(Integer listId);
}
