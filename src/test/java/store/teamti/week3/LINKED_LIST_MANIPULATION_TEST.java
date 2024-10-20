package store.teamti.week3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class LINKED_LIST_MANIPULATION_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                LINKED_LIST_MANIPULATION::main,
                "src/test/resources/week3/LINKED_LIST_MANIPULATION_TEST/valid_input.txt",
                "src/test/resources/week3/LINKED_LIST_MANIPULATION_TEST/valid_output.txt"
        );
    }
}