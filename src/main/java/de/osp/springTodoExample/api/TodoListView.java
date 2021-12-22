package de.osp.springTodoExample.api;

import de.osp.springTodoExample.model.Entry;
import java.util.List;

public class TodoListView {
    private Integer id;
    private String description;
    private List<Entry> entries;

    public TodoListView(Integer id, String description, List<Entry> entries) {
        this.id = id;
        this.description = description;
        this.entries = entries;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Entry> getEntries() {
        return entries;
    }
}
