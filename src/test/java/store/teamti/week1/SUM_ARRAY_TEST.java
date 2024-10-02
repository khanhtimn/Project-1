package store.teamti.week1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
class SUM_ARRAY_TEST {

    private String runTest(String input) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SUM_ARRAY.main(new String[]{});

        return out.toString().trim();
    }

    @Test
    void testSmallArray() throws IOException {
        String input = "4\n3 2 5 4\n";
        String expectedOutput = "14";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testSingleElementArray() throws IOException {
        String input = "1\n100\n";
        String expectedOutput = "100";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testLargeArray() throws IOException {
        String input = "1000000\n" + "1 ".repeat(1000000) + "\n";
        String expectedOutput = "1000000";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testNegativeNumbersArray() throws IOException {
        String input = "5\n-1 -2 -3 -4 -5\n";
        String expectedOutput = "-15";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testMixedPositiveAndNegativeNumbers() throws IOException {
        String input = "6\n10 -10 20 -20 30 -30\n";
        String expectedOutput = "0";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testAllZerosArray() throws IOException {
        String input = "3\n0 0 0\n";
        String expectedOutput = "0";
        assertEquals(expectedOutput, runTest(input));
    }
}