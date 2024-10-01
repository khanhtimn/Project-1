package store.teamti;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class C_K_N_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                C_K_N::main,
                "src/test/resources/C_K_N_TEST/valid_input.txt",
                "src/test/resources/C_K_N_TEST/valid_output.txt"
        );
    }
}