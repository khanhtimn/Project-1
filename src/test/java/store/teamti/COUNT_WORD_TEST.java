package store.teamti;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class COUNT_WORD_TEST {

    @Test
    void singleWord() throws IOException {
        TestUtils.testMethodWithIO(
                COUNT_WORD::main,
                "src/test/resources/COUNT_WORD_TEST/single_word_input.txt",
                "src/test/resources/COUNT_WORD_TEST/single_word_output.txt"
        );
    }

    @Test
    void multipleWords() throws IOException {
        TestUtils.testMethodWithIO(
                COUNT_WORD::main,
                "src/test/resources/COUNT_WORD_TEST/multiple_words_input.txt",
                "src/test/resources/COUNT_WORD_TEST/multiple_words_output.txt"
        );
    }

    @Test
    void emptyInput() throws IOException {
        TestUtils.testMethodWithIO(
                COUNT_WORD::main,
                "src/test/resources/COUNT_WORD_TEST/empty_input.txt",
                "src/test/resources/COUNT_WORD_TEST/empty_output.txt"
        );
    }

    @Test
    void inputWithMultipleSpaces() throws IOException {
        TestUtils.testMethodWithIO(
                COUNT_WORD::main,
                "src/test/resources/COUNT_WORD_TEST/multiple_spaces_input.txt",
                "src/test/resources/COUNT_WORD_TEST/multiple_spaces_output.txt"
        );
    }

    @Test
    void inputWithNewLines() throws IOException {
        TestUtils.testMethodWithIO(
                COUNT_WORD::main,
                "src/test/resources/COUNT_WORD_TEST/new_lines_input.txt",
                "src/test/resources/COUNT_WORD_TEST/new_lines_output.txt"
        );
    }

}