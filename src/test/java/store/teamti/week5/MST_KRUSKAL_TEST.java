package store.teamti.week5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
class MST_KRUSKAL_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                MST_KRUSKAL::main,
                "src/test/resources/week5/MST_KRUSKAL_TEST/valid_input.txt",
                "src/test/resources/week5/MST_KRUSKAL_TEST/valid_output.txt"
        );
    }
}