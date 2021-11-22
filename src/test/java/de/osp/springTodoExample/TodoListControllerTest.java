package de.osp.springTodoExample;

import de.osp.springTodoExample.api.TodoListView;
import de.osp.springTodoExample.repo.TodoListRepository;
import io.restassured.RestAssured;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class TodoListControllerTest {

    @Autowired
    private TodoListRepository todoListRepository;

    @AfterEach
    public void tearDown() {
        todoListRepository.deleteAll();
    }

    @Test
    public void testAddTodoList() {
        RestAssured.port = 8080;

        // 1. TodoList Anlegen
        RestAssured.given()
                .body("Küchenliste")
                .when()
                .post("/todoList")
                .then()
                .statusCode(200);

        Assertions.assertThat(todoListRepository.findAll().size()).isOne();
    }

    @Test
    public void testAll() {
        RestAssured.port = 8080;

        // 1. TodoList Anlegen
        RestAssured.given()
                .body("Küchenliste")
                .when()
                .post("/todoList")
                .then()
                .statusCode(200);

        final var todoList = todoListRepository.findAll().get(0);
        // 2. TodoList Entry hinzufügen
        RestAssured.given()
                .body("Seife")
                .pathParam("todoListId", todoList.getId())
                .when()
                .post("/todoList/{todoListId}/entry")
                .then()
                .statusCode(200);

        // 3. TodoList abfragen
        final List<TodoListView> todoLists = RestAssured.get("/todoList").then().
                statusCode(200)
                .extract()
                .body()
                .jsonPath().getList(".", TodoListView.class);

        // 4. TodoList validieren
        Assertions.assertThat(todoLists.size()).isOne();
        final var firstTodoList = todoLists.get(0);
        Assertions.assertThat(firstTodoList.getEntries().size()).isOne();
        Assertions.assertThat(firstTodoList.getEntries().get(0).getText()).isEqualTo("Seife");
    }

    @Test
    public void testDeleteEntry() {
        RestAssured.port = 8080;

        // 1. TodoList Anlegen
        RestAssured.given()
                .body("Küchenliste")
                .when()
                .post("/todoList")
                .then()
                .statusCode(200);

        final var todoList = todoListRepository.findAll().get(0);
        // 2. TodoList Entry hinzufügen
        RestAssured.given()
                .body("Seife")
                .pathParam("todoListId", todoList.getId())
                .when()
                .post("/todoList/{todoListId}/entry")
                .then()
                .statusCode(200);

    }
}
