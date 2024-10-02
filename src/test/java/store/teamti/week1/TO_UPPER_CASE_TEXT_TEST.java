package store.teamti.week1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class TO_UPPER_CASE_TEXT_TEST {

    @Test
    void testToUpperCaseText() throws IOException {
        TestUtils.testMethodWithIO(
                TO_UPPER_CASE_TEXT::main,
                "src/test/resources/week1/TO_UPPER_CASE_TEXT_TEST/input.txt",
                "src/test/resources/week1/TO_UPPER_CASE_TEXT_TEST/output.txt"
        );
    }
}
