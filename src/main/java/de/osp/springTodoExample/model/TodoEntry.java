package de.osp.springTodoExample.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class TodoEntry {

    @Id
    private UUID id;
    private String name;

    public TodoEntry() {
        name = "";
        id = UUID.randomUUID();
    }

    public TodoEntry(final String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setTitle(final String name) {
        this.name = name;
    }

}
