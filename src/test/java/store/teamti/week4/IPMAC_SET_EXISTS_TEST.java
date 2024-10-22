package store.teamti.week4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import store.teamti.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class IPMAC_SET_EXISTS_TEST {
    @Test
    void validInput() throws IOException {
        TestUtils.testMethodWithIO(
                IPMAC_SET_EXISTS::main,
                "src/test/resources/week4/IPMAC_SET_EXISTS_TEST/valid_input.txt",
                "src/test/resources/week4/IPMAC_SET_EXISTS_TEST/valid_output.txt"
        );
    }

}