package store.teamti;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ADD_SUBTRACT_MUL_DIV_A_B_TEST {

    private String runTest(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ADD_SUBTRACT_MUL_DIV_A_B.main(new String[]{});

        return out.toString().trim();
    }

    @Test
    void testSimpleCase1() {
        String input = "9 4\n";
        String expectedOutput = "13 5 36 2";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testSimpleCase2() {
        String input = "10 5\n";
        String expectedOutput = "15 5 50 2";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testWithSameNumbers() {
        String input = "6 6\n";
        String expectedOutput = "12 0 36 1";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testWithLargeNumbers() {
        String input = "1000 500\n";
        String expectedOutput = "1500 500 500000 2";
        assertEquals(expectedOutput, runTest(input));
    }

    @Test
    void testWithEdgeCaseMinimumNumbers() {
        String input = "1 1\n";
        String expectedOutput = "2 0 1 1";
        assertEquals(expectedOutput, runTest(input));
    }
}
