package store.teamti;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    @FunctionalInterface
    public interface IOExceptionThrowingMethod {
        void accept(String[] args) throws IOException;
    }

    public static void testMethodWithIO(IOExceptionThrowingMethod method, String inputFilePath, String expectedOutputFilePath) throws IOException {
        String input = new String(Files.readAllBytes(Paths.get(inputFilePath)));
        String expectedOutput = new String(Files.readAllBytes(Paths.get(expectedOutputFilePath))).trim();

        InputStream originalIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        try {
            method.accept(new String[]{});

            String actualOutput = out.toString().trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}