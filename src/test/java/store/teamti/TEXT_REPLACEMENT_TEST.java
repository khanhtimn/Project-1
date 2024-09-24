package store.teamti;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class TEXT_REPLACEMENT_TEST {
    @Test
    void replaceSubstring() throws IOException {
        TestUtils.testMethodWithIO(
                TEXT_REPLACEMENT::main,
                "src/test/resources/TEXT_REPLACEMENT_TEST/replace_substring_input.txt",
                "src/test/resources/TEXT_REPLACEMENT_TEST/replace_substring_output.txt"
        );
    }

    @Test
    void noReplacementNeeded() throws IOException {
        TestUtils.testMethodWithIO(
                TEXT_REPLACEMENT::main,
                "src/test/resources/TEXT_REPLACEMENT_TEST/no_replacement_input.txt",
                "src/test/resources/TEXT_REPLACEMENT_TEST/no_replacement_output.txt"
        );
    }


    @Test
    void patternNotFound() throws IOException {
        TestUtils.testMethodWithIO(
                TEXT_REPLACEMENT::main,
                "src/test/resources/TEXT_REPLACEMENT_TEST/pattern_not_found_input.txt",
                "src/test/resources/TEXT_REPLACEMENT_TEST/pattern_not_found_output.txt"
        );
    }

    @Test
    void emptyText() throws IOException {
        TestUtils.testMethodWithIO(
                TEXT_REPLACEMENT::main,
                "src/test/resources/TEXT_REPLACEMENT_TEST/empty_text_input.txt",
                "src/test/resources/TEXT_REPLACEMENT_TEST/empty_text_output.txt"
        );
    }

    @Test
    void emptyPattern() throws IOException {
        TestUtils.testMethodWithIO(
                TEXT_REPLACEMENT::main,
                "src/test/resources/TEXT_REPLACEMENT_TEST/empty_pattern_input.txt",
                "src/test/resources/TEXT_REPLACEMENT_TEST/empty_pattern_output.txt"
        );
    }
}