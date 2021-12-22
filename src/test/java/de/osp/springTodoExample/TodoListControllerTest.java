package de.osp.springTodoExample;

import de.osp.springTodoExample.api.TodoListView;
import de.osp.springTodoExample.model.TodoList;
import de.osp.springTodoExample.repository.TodoListRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class TodoListControllerTest {

    @Autowired
    private TodoListRepository todoListRepository;

    @BeforeEach
    public void reset(){
        todoListRepository.deleteAll();
    }

    @Test
    public void testAll() {
        var todoList = new TodoList();
        todoList.setDescription("Testliste");
        todoListRepository.save(todoList);

        RestAssured.port = 8080;
        var views = RestAssured.get("/").then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".", TodoListView.class);

        Assertions.assertThat(views).hasSize(1);
        Assertions.assertThat(views.get(0).getDescription()).isEqualTo(todoList.getDescription());
    }
    @Test
    public void testAddTodoList(){
        String description = "Weihnachtsliste";
        RestAssured.given()
                .body(description)
                .when()
                .post("/create")
                .then()
                .statusCode(200);

        var todoLists=todoListRepository.findAll();
        Assertions.assertThat(todoLists).hasSize(1);
        Assertions.assertThat(todoLists.get(0).getDescription()).isEqualTo(description);
    }
}
