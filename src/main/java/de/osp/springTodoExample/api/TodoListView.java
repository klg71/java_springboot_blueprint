package de.osp.springTodoExample.api;

import de.osp.springTodoExample.model.Entry;
import java.util.List;

public class TodoListView {
    private final Integer id;

    private final String name;

    private final List<Entry> entries;

    public TodoListView(Integer todoListId, String name, List<Entry> entries) {
        this.id = todoListId;
        this.name = name;
        this.entries = entries;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Entry> getEntries() {
        return entries;
    }

}
