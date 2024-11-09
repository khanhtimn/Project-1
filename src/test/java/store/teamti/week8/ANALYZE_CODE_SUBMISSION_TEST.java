package store.teamti.week8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
class ANALYZE_CODE_SUBMISSION_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                ANALYZE_CODE_SUBMISSION::main,
                "src/test/resources/week8/ANALYZE_CODE_SUBMISSION_TEST/valid_input.txt",
                "src/test/resources/week8/ANALYZE_CODE_SUBMISSION_TEST/valid_output.txt"
        );
    }
}