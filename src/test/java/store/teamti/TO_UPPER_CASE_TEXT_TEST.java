package store.teamti;

import org.junit.jupiter.api.Test;
import java.io.*;

class TO_UPPER_CASE_TEXT_TEST {

    @Test
    void testToUpperCaseText() throws IOException {
        TestUtils.testMethodWithIO(
                TO_UPPER_CASE_TEXT::main,
                "src/test/resources/TO_UPPER_CASE_TEXT_TEST/input.txt",
                "src/test/resources/TO_UPPER_CASE_TEXT_TEST/output.txt"
        );
    }
}
