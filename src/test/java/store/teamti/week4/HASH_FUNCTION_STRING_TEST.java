package store.teamti.week4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
class HASH_FUNCTION_STRING_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                HASH_FUNCTION_STRING::main,
                "src/test/resources/week4/HASH_FUNCTION_STRING_TEST/valid_input.txt",
                "src/test/resources/week4/HASH_FUNCTION_STRING_TEST/valid_output.txt"
        );
    }
}