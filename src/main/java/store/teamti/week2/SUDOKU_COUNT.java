package store.teamti.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Program to compute the number of possible solutions for a given partial Sudoku puzzle.
 *
 * <p>Description:</p>
 * Given a 9x9 Sudoku grid, the program fills in the empty cells (denoted by 0) with numbers
 * from 1 to 9 such that:
 * - Each row contains distinct numbers.
 * - Each column contains distinct numbers.
 * - Each 3x3 sub-grid contains distinct numbers.
 * The goal is to count the number of valid solutions that complete the given partial grid.
 *
 * <p>Input:</p>
 * - A 9-line input, where each line represents one row of the Sudoku grid.
 * - Each line contains 9 integers between 0 and 9 (0 represents an empty cell).
 *
 * <p>Output:</p>
 * - The number of distinct valid solutions that can complete the puzzle.
 *
 * <p>Example:</p>
 *
 * <pre>
 * Input:
 * 0 0 3 4 0 0 0 8 9
 * 0 0 6 7 8 9 0 2 3
 * 0 8 0 0 2 3 4 5 6
 * 0 0 4 0 6 5 0 9 7
 * 0 6 0 0 9 0 0 1 4
 * 0 0 7 2 0 4 3 6 5
 * 0 3 0 6 0 2 0 7 8
 * 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0
 *
 * Output:
 * 64
 * </pre>
 *
 * <p>Note:</p>
 * The program calculates and displays the total number of valid solutions found for the given
 * partial Sudoku table, ensuring that the output is the total number of ways to complete the grid.
 */
public class SUDOKU_COUNT {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;
    private static long solutionCount = 0;
    private static final int[][] grid = new int[SIZE][SIZE];

    public static void main(String[] args) throws IOException {
        readInput();
        countSolutions(0, 0);
        System.out.println(solutionCount);
    }

    private static void readInput() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < SIZE; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < SIZE; j++) {
                    grid[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
    }

    private static void countSolutions(int row, int col) {
        if (row == SIZE) {
            solutionCount++;
            return;
        }

        int nextRow = (col == SIZE - 1) ? row + 1 : row;
        int nextCol = (col == SIZE - 1) ? 0 : col + 1;

        if (grid[row][col] != EMPTY) {
            countSolutions(nextRow, nextCol);
            return;
        }

        for (int num = 1; num <= SIZE; num++) {
            if (isValid(row, col, num)) {
                grid[row][col] = num;
                countSolutions(nextRow, nextCol);
                grid[row][col] = EMPTY;
            }
        }
    }

    private static boolean isValid(int row, int col, int num) {
        for (int j = 0; j < SIZE; j++) {
            if (grid[row][j] == num) return false;
        }

        for (int i = 0; i < SIZE; i++) {
            if (grid[i][col] == num) return false;
        }

        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxRow + i][boxCol + j] == num) return false;
            }
        }

        return true;
    }
}
