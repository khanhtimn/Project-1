package store.teamti.week6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
class MAX_FLOW_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                MAX_FLOW::main,
                "src/test/resources/week6/MAX_FLOW_TEST/valid_input.txt",
                "src/test/resources/week6/MAX_FLOW_TEST/valid_output.txt"
        );
    }
}