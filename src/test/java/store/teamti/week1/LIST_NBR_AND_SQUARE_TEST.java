package store.teamti.week1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class LIST_NBR_AND_SQUARE_TEST {

    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                LIST_NBR_AND_SQUARE::main,
                "src/test/resources/week1/LIST_NBR_AND_SQUARE_TEST/valid_input.txt",
                "src/test/resources/week1/LIST_NBR_AND_SQUARE_TEST/valid_output.txt"
        );
    }

    @Test
    void minimumInput() throws IOException {
        TestUtils.testMethodWithIO(
                LIST_NBR_AND_SQUARE::main,
                "src/test/resources/week1/LIST_NBR_AND_SQUARE_TEST/min_input.txt",
                "src/test/resources/week1/LIST_NBR_AND_SQUARE_TEST/min_output.txt"
        );
    }

    @Test
    void maximumInput() throws IOException {
        TestUtils.testMethodWithIO(
                LIST_NBR_AND_SQUARE::main,
                "src/test/resources/week1/LIST_NBR_AND_SQUARE_TEST/max_input.txt",
                "src/test/resources/week1/LIST_NBR_AND_SQUARE_TEST/max_output.txt"
        );
    }

    @Test
    void invalidInputTooLow() throws RuntimeException, IOException {
            TestUtils.testMethodWithIO(
                    LIST_NBR_AND_SQUARE::main,
                    "src/test/resources/week1/LIST_NBR_AND_SQUARE_TEST/invalid_input_low.txt",
                    "src/test/resources/week1/LIST_NBR_AND_SQUARE_TEST/invalid_output.txt"
            );
    }

    @Test
    void invalidInputTooHigh() throws RuntimeException, IOException {
            TestUtils.testMethodWithIO(
                    LIST_NBR_AND_SQUARE::main,
                    "src/test/resources/week1/LIST_NBR_AND_SQUARE_TEST/invalid_input_high.txt",
                    "src/test/resources/week1/LIST_NBR_AND_SQUARE_TEST/invalid_output.txt"
            );
    }

}