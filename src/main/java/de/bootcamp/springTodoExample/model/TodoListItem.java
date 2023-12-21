package de.bootcamp.springTodoExample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class TodoListItem {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private Boolean isDone;
    private Integer todoListId;
    private LocalDate deadline;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public Integer getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Integer todoListId) {
        this.todoListId = todoListId;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
