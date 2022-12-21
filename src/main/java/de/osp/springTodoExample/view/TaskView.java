package de.osp.springTodoExample.view;

import de.osp.springTodoExample.model.Priority;

public class TaskView {

    private String title;

    private String description;

    private Boolean isDone;

    private Priority priority;

    public TaskView(String title, String description, Boolean isDone, Priority priority) {
        this.title = title;
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
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

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
