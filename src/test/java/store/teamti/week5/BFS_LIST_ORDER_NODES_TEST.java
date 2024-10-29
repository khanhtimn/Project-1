package store.teamti.week5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
class BFS_LIST_ORDER_NODES_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                BFS_LIST_ORDER_NODES::main,
                "src/test/resources/week5/BFS_LIST_ORDER_NODES_TEST/valid_input.txt",
                "src/test/resources/week5/BFS_LIST_ORDER_NODES_TEST/valid_output.txt"
        );
    }
}