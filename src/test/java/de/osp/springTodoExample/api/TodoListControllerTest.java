package de.osp.springTodoExample.api;

import de.osp.springTodoExample.repo.TodoListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TodoListControllerTest {

    @Autowired
    private TodoListRepository todoListRepository;

    @AfterEach
    public void tearDown() {
        todoListRepository.deleteAll();
    }

    @Test
    public void testGetAll() {
    }

}