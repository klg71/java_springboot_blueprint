package de.osp.springTodoExample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer listId;

    @NotNull
    private String title;

    private String description;

    private Boolean isDone;

    private Priority priority;

    public Task(Integer listId, String title, String description, Boolean isDone, Priority priority) {
        this.listId = listId;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    public Task() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean done) {
        this.isDone = done;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority){
        this.priority = priority;
    }
}
