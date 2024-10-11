package store.teamti.week3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class PARENTHESIS_CHECK_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                PARENTHESIS_CHECK::main,
                "src/test/resources/week3/PARENTHESIS_CHECK_TEST/valid_input.txt",
                "src/test/resources/week3/PARENTHESIS_CHECK_TEST/valid_output.txt"
        );
    }
}