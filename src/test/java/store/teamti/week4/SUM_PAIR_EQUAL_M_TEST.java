package store.teamti.week4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class SUM_PAIR_EQUAL_M_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                SUM_PAIR_EQUAL_M::main,
                "src/test/resources/week4/SUM_PAIR_EQUAL_M_TEST/valid_input.txt",
                "src/test/resources/week4/SUM_PAIR_EQUAL_M_TEST/valid_output.txt"
        );
    }
}