package store.teamti.week1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ_TEST {

    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ::main,
                "src/test/resources/week1/COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ_TEST/valid_input.txt",
                "src/test/resources/week1/COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ_TEST/valid_output.txt"
        );
    }

    @Test
    void minimumInput() throws IOException {
        TestUtils.testMethodWithIO(
                COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ::main,
                "src/test/resources/week1/COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ_TEST/min_input.txt",
                "src/test/resources/week1/COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ_TEST/min_output.txt"
        );
    }

    @Test()
    void maximumInput() throws IOException {
        TestUtils.testMethodWithIO(
                COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ::main,
                "src/test/resources/week1/COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ_TEST/max_input.txt",
                "src/test/resources/week1/COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ_TEST/max_output.txt"
        );
    }

    @Test
    void invalidInputTooLow() throws IOException {
        TestUtils.testMethodWithIO(
                COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ::main,
                "src/test/resources/week1/COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ_TEST/invalid_input_low.txt",
                "src/test/resources/week1/COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ_TEST/invalid_output.txt"
        );
    }

    @Test
    void invalidInputTooHigh() throws IOException {
        TestUtils.testMethodWithIO(
                COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ::main,
                "src/test/resources/week1/COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ_TEST/invalid_input_high.txt",
                "src/test/resources/week1/COUNT_ODD_EVEN_ELEMENTS_FROM_SEQ_TEST/invalid_output.txt"
        );
    }

}