package store.teamti;

import org.junit.jupiter.api.Test;
import java.io.*;

class BASIC_QUERY_ARRAY_TEST {

    @Test
    void testBasicQueryArray() throws IOException {
        TestUtils.testMethodWithIO(
                BASIC_QUERY_ARRAY::main,
                "src/test/resources/BASIC_QUERY_ARRAY_TEST/input.txt",
                "src/test/resources/BASIC_QUERY_ARRAY_TEST/output.txt"
        );
    }
}
