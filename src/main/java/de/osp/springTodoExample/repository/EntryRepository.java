package de.osp.springTodoExample.repository;

import de.osp.springTodoExample.model.Entry;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {
    List<Entry> findAllByTodoListId(Integer todoListId);
}
