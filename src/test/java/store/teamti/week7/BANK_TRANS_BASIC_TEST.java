package store.teamti.week7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
class BANK_TRANS_BASIC_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                BANK_TRANS_BASIC::main,
                "src/test/resources/week7/BANK_TRANS_BASIC_TEST/valid_input.txt",
                "src/test/resources/week7/BANK_TRANS_BASIC_TEST/valid_output.txt"
        );
    }
}