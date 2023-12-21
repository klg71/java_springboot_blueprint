package de.bootcamp.springTodoExample.web;

import de.bootcamp.springTodoExample.model.TodoCategory;
import de.bootcamp.springTodoExample.model.TodoList;
import de.bootcamp.springTodoExample.model.TodoListItem;
import de.bootcamp.springTodoExample.repo.TodoListItemRepository;
import de.bootcamp.springTodoExample.repo.TodoListRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todoList")
public class TodoListController {
    private final TodoListRepository todoListRepository;
    private final TodoListItemRepository todoListItemRepository;

    public TodoListController(TodoListRepository todoListRepository,
                              TodoListItemRepository todoListItemRepository) {
        this.todoListRepository = todoListRepository;
        this.todoListItemRepository = todoListItemRepository;
    }

    @GetMapping
    public List<TodoListView> get() {
        var entries = todoListRepository.findAll();
        var result = new LinkedList<TodoListView>();
        for (var entry : entries) {
            var view = new TodoListView(entry.getName(), entry.getId(),
                    entries(entry.getId()), entry.getCategory());
            result.add(view);
        }
        return result;
    }

    @PostMapping("/")
    public TodoList insert(@RequestBody String name,
                           @RequestParam("category") TodoCategory category) {
        var newTodoList = new TodoList();
        newTodoList.setName(name);
        newTodoList.setCategory(category);
        return todoListRepository.save(newTodoList);
    }

    @GetMapping("/{todoListId}/")
    public List<TodoListItem> entries(@PathVariable("todoListId")
                                      Integer todoListId) {
        return todoListItemRepository.findAllByTodoListId(todoListId);
    }

    @PostMapping("/{todoListId}/")
    public TodoListItem addEntry(@PathVariable("todoListId") Integer todoListId,
                                 @RequestBody String title) {
        var entry = new TodoListItem();
        entry.setTodoListId(todoListId);
        entry.setTitle(title);
        entry.setDone(false);
        return todoListItemRepository.save(entry);
    }

    @PutMapping("/setDone/{todoListEntryId}")
    public Optional<TodoListItem> setDone(
            @PathVariable("todoListEntryId") Integer todoListEntryId) {
        var result = todoListItemRepository.findById(todoListEntryId);
        if (result.isPresent()) {
            var item = result.get();
            item.setDone(true);
            return Optional.of(todoListItemRepository.save(item));
        } else {
            return Optional.empty();
        }
    }

    @PutMapping("/setDeadline/{todoListEntryId}")
    public Optional<TodoListItem> setDeadline(
            @PathVariable("todoListEntryId") Integer todoListEntryId,
            @RequestBody String dateString) {

        var format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        var date = LocalDate.parse(dateString, format);

        var result = todoListItemRepository.findById(todoListEntryId);
        if (result.isPresent()) {
            var item = result.get();
            item.setDeadline(date);
            return Optional.of(todoListItemRepository.save(item));
        } else {
            return Optional.empty();
        }
    }

    @PostMapping("/generateTestData")
    public void generateTestData() {
        var einkauf = insert("Einkaufsliste", TodoCategory.GROCERIES);
        var work = insert("Work", TodoCategory.WORK);

        addEntry(einkauf.getId(), "Tomaten");
        setDeadline(1, "12.02.2023");
        addEntry(einkauf.getId(), "Gurken");

        addEntry(work.getId(), "Meeting");
        addEntry(work.getId(), "Report");
    }

    @GetMapping("/deadlineEnding/{deadline}")
    public List<TodoListItem> deadlineEnding(@PathVariable("deadline") String dueDate) {
        var format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        var date = LocalDate.parse(dueDate, format);
        var allTodoListItems = todoListItemRepository.findAll();
        var allTodoListItemsEnding = new LinkedList<TodoListItem>();
        for (var entry : allTodoListItems) {
            if (Objects.equals(entry.getDeadline(), date)) {
                allTodoListItemsEnding.add(entry);
            }
        }
        return allTodoListItemsEnding;
    }

    @Transactional
    @DeleteMapping("/deleteTodoList/{todoListId}")
    public void deleteTodoList(@PathVariable("todoListId") Integer todoListId) {
        todoListItemRepository.deleteAllByTodoListId(todoListId);
        todoListRepository.deleteById(todoListId);
    }
}
