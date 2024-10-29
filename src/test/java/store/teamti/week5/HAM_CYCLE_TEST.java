package store.teamti.week5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class HAM_CYCLE_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                HAM_CYCLE::main,
                "src/test/resources/week5/HAM_CYCLE_TEST/valid_input.txt",
                "src/test/resources/week5/HAM_CYCLE_TEST/valid_output.txt"
        );
    }
}