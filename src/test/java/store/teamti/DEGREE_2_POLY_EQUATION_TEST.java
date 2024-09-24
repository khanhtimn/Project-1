package store.teamti;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
class DEGREE_2_POLY_EQUATION_TEST {

    @Test
    void testNoSolution() throws IOException {
        TestUtils.testMethodWithIO(
                DEGREE_2_POLY_EQUATION::main,
                "src/test/resources/DEGREE_2_POLY_EQUATION_TEST/input_1.txt",
                "src/test/resources/DEGREE_2_POLY_EQUATION_TEST/output_1.txt"
        );
    }

    @Test
    void testOneSolution() throws IOException {
        TestUtils.testMethodWithIO(
                DEGREE_2_POLY_EQUATION::main,
                "src/test/resources/DEGREE_2_POLY_EQUATION_TEST/input_2.txt",
                "src/test/resources/DEGREE_2_POLY_EQUATION_TEST/output_2.txt"
        );
    }

    @Test
    void testTwoSolutions() throws IOException {
        TestUtils.testMethodWithIO(
                DEGREE_2_POLY_EQUATION::main,
                "src/test/resources/DEGREE_2_POLY_EQUATION_TEST/input_3.txt",
                "src/test/resources/DEGREE_2_POLY_EQUATION_TEST/output_3.txt"
        );
    }


}