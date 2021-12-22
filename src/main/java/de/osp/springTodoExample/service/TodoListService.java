package de.osp.springTodoExample.service;

import de.osp.springTodoExample.model.Entry;
import de.osp.springTodoExample.model.EntryStatus;
import de.osp.springTodoExample.model.TodoList;
import de.osp.springTodoExample.repository.EntryRepository;
import de.osp.springTodoExample.repository.TodoListRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final EntryRepository entryRepository;

    public TodoListService(TodoListRepository todoListRepository, EntryRepository entryRepository) {
        this.todoListRepository = todoListRepository;
        this.entryRepository = entryRepository;
    }

    public Entry addEntry(Integer todoListId, LocalDateTime dueDate, String description, Integer priority) {
        if (priority < 1 || priority > 5) {
            throw new RuntimeException("Priority: " + priority + " is not permitted please choose from 1..5");
        }
        if (dueDate.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("dueDate is only allowed after today");
        }
        if (description.isBlank()) {
            throw new RuntimeException("description cannot be blank");
        }
        if (!todoListRepository.existsById(todoListId)) {
            throw new EntityNotFoundException("TodoList with id: " + todoListId + " does not exist.");
        }

        final var newEntry = new Entry();
        newEntry.setDescription(description);
        newEntry.setPriority(priority);
        newEntry.setDueDate(dueDate);
        newEntry.setTodoListId(todoListId);
        newEntry.setEntryStatus(EntryStatus.PENDING);

        return entryRepository.save(newEntry);
    }

    public Entry updateStatus(Integer entryId,EntryStatus newStatus){
        var optionalEntry = entryRepository.findById(entryId);
        if(optionalEntry.isEmpty()){
            throw new EntityNotFoundException("Entry with id: "+entryId+" not found");
        }
        var entry = optionalEntry.get();
        entry.setEntryStatus(newStatus);

        return entryRepository.save(entry);
    }

    public TodoList createTodoList(String description) {
        final var todoList = new TodoList();
        todoList.setDescription(description);

        return todoListRepository.save(todoList);
    }

    public List<TodoList> all() {
        return todoListRepository.findAll();
    }

    public List<Entry> getEntries(Integer id) {
        return entryRepository.findAllByTodoListId(id);
    }

    public void deleteEntry(Integer entryId) {
        entryRepository.deleteById(entryId);
    }
}
