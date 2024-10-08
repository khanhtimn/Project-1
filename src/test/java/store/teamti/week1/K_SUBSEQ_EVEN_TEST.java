package store.teamti.week1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class K_SUBSEQ_EVEN_TEST {

    @Test
    void testBasicKSubsequenceEven() throws IOException {
        TestUtils.testMethodWithIO(
                K_SUBSEQ_EVEN::main,
                "src/test/resources/week1/K_SUBSEQ_EVEN_TEST/input.txt",
                "src/test/resources/week1/K_SUBSEQ_EVEN_TEST/output.txt"
        );
    }

}