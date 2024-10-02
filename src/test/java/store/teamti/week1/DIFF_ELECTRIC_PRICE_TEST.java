package store.teamti.week1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class DIFF_ELECTRIC_PRICE_TEST {

    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                DIFF_ELECTRIC_PRICE::main,
                "src/test/resources/week1/DIFF_ELECTRIC_PRICE_TEST/valid_input.txt",
                "src/test/resources/week1/DIFF_ELECTRIC_PRICE_TEST/valid_output.txt"
        );
    }

    @Test
    void zeroInput() throws IOException {
        TestUtils.testMethodWithIO(
                DIFF_ELECTRIC_PRICE::main,
                "src/test/resources/week1/DIFF_ELECTRIC_PRICE_TEST/zero_input.txt",
                "src/test/resources/week1/DIFF_ELECTRIC_PRICE_TEST/zero_output.txt"
        );
    }

    @Test
    void invalidInputNegative() throws IOException {
        TestUtils.testMethodWithIO(
                DIFF_ELECTRIC_PRICE::main,
                "src/test/resources/week1/DIFF_ELECTRIC_PRICE_TEST/invalid_input_negative.txt",
                "src/test/resources/week1/DIFF_ELECTRIC_PRICE_TEST/invalid_output.txt"
        );
    }
}