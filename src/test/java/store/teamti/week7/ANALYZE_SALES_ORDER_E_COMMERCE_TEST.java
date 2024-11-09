package store.teamti.week7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
class ANALYZE_SALES_ORDER_E_COMMERCE_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                ANALYZE_SALES_ORDER_E_COMMERCE::main,
                "src/test/resources/week7/ANALYZE_SALES_ORDER_E_COMMERCE_TEST/valid_input.txt",
                "src/test/resources/week7/ANALYZE_SALES_ORDER_E_COMMERCE_TEST/valid_output.txt"
        );
    }
}