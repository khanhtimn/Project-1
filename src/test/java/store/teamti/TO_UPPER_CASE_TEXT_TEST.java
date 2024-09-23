package store.teamti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class TO_UPPER_CASE_TEXT_TEST {

    @Test
    void testToUpperCaseText() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("src/test/resources/TO_UPPER_CASE_TEXT_TEST/input.txt")));

        String expectedOutput = new String(Files.readAllBytes(Paths.get("src/test/resources/TO_UPPER_CASE_TEXT_TEST/output.txt")));

        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);

        System.setIn(in);
        System.setOut(printStream);

        TO_UPPER_CASE_TEXT.main(new String[]{});

        String actualOutput = out.toString().trim();
        assertEquals(expectedOutput.trim(), actualOutput);
    }
}
