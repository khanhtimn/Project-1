package store.teamti.week6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
class SHORTEST_PATH_DIJKSTRA_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                SHORTEST_PATH_DIJKSTRA::main,
                "src/test/resources/week6/SHORTEST_PATH_DIJKSTRA_TEST/valid_input.txt",
                "src/test/resources/week6/SHORTEST_PATH_DIJKSTRA_TEST/valid_output.txt"
        );
    }
}