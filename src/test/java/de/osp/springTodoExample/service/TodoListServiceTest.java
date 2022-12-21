package de.osp.springTodoExample.service;

import de.osp.springTodoExample.model.Priority;
import de.osp.springTodoExample.model.Task;
import de.osp.springTodoExample.model.TodoList;
import de.osp.springTodoExample.repo.TaskRepository;
import de.osp.springTodoExample.repo.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.when;

class TodoListServiceTest {

    /**
     * Dokumentation von Mockito https://site.mockito.org/
     * Baeldung link https://www.baeldung.com/mockito-series zu einem Tutorial
     */

    @Mock
    private TodoListRepository todoListRepository;

    @Mock
    private TaskRepository taskRepository;

    private TodoListService service;

    @BeforeEach
    void init() {
        //durch die Dependency Injection können wir jetzt hier unsere mocks injecten lassen und später in den Methoden
        //sachen zurückgeben wie wir wollen
        service = new TodoListService(todoListRepository, taskRepository);

        //Die Caches leeren, damit Mock die Mockitos neu initialisiert.
        //Das kann ansonsten mal zu Problemen führen.
        clearAllCaches();
    }

    @Test
    void testGetAll() {
        var task = new Task(2, "test", "test", false, Priority.LOW);
        var list = new TodoList(1, "test", "test", LocalDateTime.now());
        //wenn die Methode aufgerufen wird, dann gib bitte das zurück
        when(todoListRepository.findAll()).thenReturn(List.of(list));
        //hier wird das when() nur getriggert, falls die methode exakt mit dem parameter 1 aufgerufen wird
        //man kann hier auch mit any() arbeiten, wodurch der parameter egal wird
        when(taskRepository.findAllByListId(1)).thenReturn(List.of(task));

        var result = service.getAllTodoLists();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getTasks()).isEqualTo(1);
        assertThat(result.get(0).getId()).isEqualTo(list.getId());

    }

}