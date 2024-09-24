package store.teamti;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class EXTRACT_YYYY_MM_DD_TEST {

    private String runTest(String input) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        EXTRACT_YYYY_MM_DD.main(new String[]{});

        return out.toString().trim();
    }

    @Test
    void testValidDate() throws IOException {
        String input = "2023-10-04\n";
        String expectedOutput = "2023 10 4";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testInvalidDateMissingLeadingZeroInDay() throws IOException {
        String input = "2023-10-4\n";
        String expectedOutput = "INCORRECT";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testInvalidDateWithSpace() throws IOException {
        String input = "2023-10 04\n";
        String expectedOutput = "INCORRECT";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testInvalidFormat() throws IOException {
        String input = "2023/10/04\n";
        String expectedOutput = "INCORRECT";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testValidDateEdgeCase() throws IOException {
        String input = "1999-01-01\n";
        String expectedOutput = "1999 1 1";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testInvalidMonth() throws IOException {
        String input = "2023-13-01\n";
        String expectedOutput = "INCORRECT";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testInvalidDay() throws IOException {
        String input = "2023-10-32\n";
        String expectedOutput = "INCORRECT";
        assertEquals(expectedOutput, runTest(input));
    }
}