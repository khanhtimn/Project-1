package store.teamti.week1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class BASIC_QUERY_ARRAY_TEST {

    @Test
    void testBasicQueryArray() throws IOException {
        TestUtils.testMethodWithIO(
                BASIC_QUERY_ARRAY::main,
                "src/test/resources/week1/BASIC_QUERY_ARRAY_TEST/input.txt",
                "src/test/resources/week1/BASIC_QUERY_ARRAY_TEST/output.txt"
        );
    }
}
