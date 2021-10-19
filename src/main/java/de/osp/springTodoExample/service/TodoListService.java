package de.osp.springTodoExample.service;

import de.osp.springTodoExample.model.Entry;
import de.osp.springTodoExample.model.TodoList;
import de.osp.springTodoExample.repo.EntryRepository;
import de.osp.springTodoExample.repo.TodoListRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {
    private final EntryRepository entryRepository;
    private final TodoListRepository todoListRepository;

    public TodoListService(EntryRepository entryRepository, TodoListRepository todoListRepository) {
        this.entryRepository = entryRepository;
        this.todoListRepository = todoListRepository;
    }

    @NonNull
    public TodoList createTodoList(final String name) {
        final TodoList newTodoList = new TodoList();
        newTodoList.setName(name);

        return todoListRepository.save(newTodoList);
    }

    @NonNull
    public Entry addEntry(final Integer todoListId, final String text) {
        if (!todoListRepository.existsById(todoListId)) {
            throw new IllegalArgumentException("TodoList with id: " + todoListId + " could not be found");
        }
        final var newEntry = new Entry();
        newEntry.setListId(todoListId);
        newEntry.setText(text);
        newEntry.setDone(false);

        return entryRepository.save(newEntry);
    }

    public List<TodoList> all() {
        return todoListRepository.findAll();
    }

    public List<Entry> getEntries(Integer todoListId) {
        return entryRepository.findAllByListId(todoListId);
    }

    public Entry setEntryDone(Integer entryId) {
        final var optionalEntry = entryRepository.findById(entryId);
        if (optionalEntry.isEmpty()) {
            throw new EntityNotFoundException("TodoListEntry with id: " + optionalEntry + " could not be found");
        }
        final var entry = optionalEntry.get();
        entry.setDone(true);

        return entryRepository.save(entry);
    }
}
