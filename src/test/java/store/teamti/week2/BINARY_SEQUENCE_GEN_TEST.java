package store.teamti.week2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class BINARY_SEQUENCE_GEN_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                BINARY_SEQUENCE_GEN::main,
                "src/test/resources/week2/BINARY_SEQUENCE_GEN_TEST/valid_input.txt",
                "src/test/resources/week2/BINARY_SEQUENCE_GEN_TEST/valid_output.txt"
        );
    }
}