package de.osp.springTodoExample;

import de.osp.springTodoExample.repository.TodoListRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class TodoListControllerTest {

    @Autowired
    private TodoListRepository todoListRepository;

    @Test
    public void testAll(){
        Assertions.assertThat(false).isTrue();
    }
}
