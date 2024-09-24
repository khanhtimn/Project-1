package store.teamti;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class CONVERT_HH_MM_SS_2_SECOND_TEST {

    @Test
    void validTimeFormat() throws IOException {
        TestUtils.testMethodWithIO(
                CONVERT_HH_MM_SS_2_SECOND::main,
                "src/test/resources/CONVERT_HH_MM_SS_2_SECOND_TEST/valid_input.txt",
                "src/test/resources/CONVERT_HH_MM_SS_2_SECOND_TEST/valid_output.txt"
        );
    }

    @Test
    void invalidTimeFormat() throws IOException {
        TestUtils.testMethodWithIO(
                CONVERT_HH_MM_SS_2_SECOND::main,
                "src/test/resources/CONVERT_HH_MM_SS_2_SECOND_TEST/invalid_input.txt",
                "src/test/resources/CONVERT_HH_MM_SS_2_SECOND_TEST/invalid_output.txt"
        );
    }

    @Test
    void outOfRangeTime() throws IOException {
        TestUtils.testMethodWithIO(
                CONVERT_HH_MM_SS_2_SECOND::main,
                "src/test/resources/CONVERT_HH_MM_SS_2_SECOND_TEST/out_of_range_input.txt",
                "src/test/resources/CONVERT_HH_MM_SS_2_SECOND_TEST/invalid_output.txt"
        );
    }

    @Test
    void emptyInput() throws IOException {
        TestUtils.testMethodWithIO(
                CONVERT_HH_MM_SS_2_SECOND::main,
                "src/test/resources/CONVERT_HH_MM_SS_2_SECOND_TEST/empty_input.txt",
                "src/test/resources/CONVERT_HH_MM_SS_2_SECOND_TEST/invalid_output.txt"
        );
    }
}