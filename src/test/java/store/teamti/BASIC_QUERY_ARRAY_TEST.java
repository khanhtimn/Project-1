package store.teamti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class BASIC_QUERY_ARRAY_TEST {

    @Test
    void testBasicQueryArray() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("src/test/resources/BASIC_QUERY_ARRAY_TEST/input.txt")));

        String expectedOutput = new String(Files.readAllBytes(Paths.get("src/test/resources/BASIC_QUERY_ARRAY_TEST/output.txt")));

        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);

        System.setIn(in);
        System.setOut(printStream);

        BASIC_QUERY_ARRAY.main(new String[]{});

        String actualOutput = out.toString().trim();
        assertEquals(expectedOutput.trim(), actualOutput);
    }
}
