package de.osp.springTodoExample.api;

import de.osp.springTodoExample.model.Priority;
import de.osp.springTodoExample.repo.TaskRepository;
import de.osp.springTodoExample.repo.TodoListRepository;
import de.osp.springTodoExample.view.TaskView;
import de.osp.springTodoExample.view.TodoListView;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * ich hatte lokal Probleme mit RestAssured und der dependency
 * daher geht der test nicht. Falles es bei euch auch nicht klappt,
 * dann kommientiert die dependency und den test aus.
 * Dann könnt ihr den anderen test ausführen
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TodoListControllerTest {

    @Autowired
    private TodoListRepository todoListRepository;
    @Autowired
    private TaskRepository taskRepository;

    @AfterEach
    public void tearDown() {
        todoListRepository.deleteAll();
        taskRepository.deleteAll();
    }

    /**
     * Doku zu RestAssured
     * https://rest-assured.io/
     * könnt ihr euch gerne mal anschauen.
     */

    @Test
    public void testSaveTodoList() {
        RestAssured.port = 8080;

        var todoListView = new TodoListView(1, "test", "test test", LocalDateTime.now(), List.of(new TaskView("test Tash",
                "test description", false, Priority.HIGH)));
        //Anlegen einer TodoList mit einem Task
        RestAssured.given()
                .body(todoListView)
                .when()
                .post("/todoList")
                .then()
                .statusCode(200);
        //überprüfen, dass die Einträge in der DB gelandet sind
        assertThat(todoListRepository.findAll().size()).isEqualTo(1);
        assertThat(todoListRepository.findAll().size()).isOne();
    }

    @Test
    public void testGetAll() {
        RestAssured.port = 8080;

        var todoListView = new TodoListView(1, "test", "test test", LocalDateTime.now(), List.of(new TaskView("test Tash",
                "test description", false, Priority.HIGH)));

        RestAssured.given()
                .body(todoListView)
                .when()
                .post("/todoList")
                .then()
                .statusCode(200);
        //abfragen des getAll Endpunkts und dann das result kontrollieren
        var result = RestAssured.get("/todoList")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath().getList(".", TodoListView.class);

        assertThat(todoListRepository.findAll().size()).isEqualTo(1);
        assertThat(todoListRepository.findAll().size()).isOne();
        assertThat(result.size()).isOne();
        assertThat(result.stream().findFirst().get().getId()).isEqualTo(todoListView.getId());
    }

    /**
     * Tobt euch hier gerne noch auch.
     * Testet die anderen Endpunkte und schaut was passiert wenn ihr unterschiedliche Werte übergebt, was passiert bei null oder so.
     */

}