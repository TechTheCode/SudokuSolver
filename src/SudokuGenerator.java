import java.util.Random;

public class SudokuGenerator {
    private SudokuBoard sudokuBoard;
    private Random random;
    private static final int size = SudokuBoard.size;
    RandomGenerator rng = new RandomGenerator();

    public SudokuGenerator() {
        this.sudokuBoard = new SudokuBoard();
        this.random = new Random();
        fillDiagonalBlocks();
        if (!sudokuGenerate(0, 0)) {
            throw new RuntimeException("Failed to generate a sudoku board");
        }
    }

    boolean sudokuGenerate(int row, int col) {
        if (col == size) {
            col = 0;
            row++;

            if (row == size) {
                return true; // Sudoku solved
            }
        }

        // Skip filled cells
        if (sudokuBoard.getCell(row, col) != 0) {
            return sudokuGenerate(row, col + 1);
        }

        for (int num = 1; num <= 9; num++) {
            if (isSafe(row, col, num)) {
                sudokuBoard.setCell(row, col, num);
                if (sudokuGenerate(row, col + 1)) {
                    return true;
                }
                sudokuBoard.setCell(row, col, 0); // Reset cell for backtracking
            }
        }
        return false; // Trigger backtracking
    }

    private boolean isSafe(int row, int col, int num) {
        // Row check
        sudokuBoard.setCell(row, col, num);
        boolean safe = SudokuValidator.isRowValid(sudokuBoard, row)
                && SudokuValidator.isColumnValid(sudokuBoard, col)
                && SudokuValidator.isBlockValid(sudokuBoard, row - row % 3, col - col % 3);
        sudokuBoard.setCell(row, col, 0); // Reset cell after check
        return safe;
    }

    private void fillDiagonalBlocks() {
        for (int i = 0; i < size; i += 3) {
            fillBlock(i, i);
        }
    }

    private void fillBlock(int row, int col) {
        Integer[] numbers = rng.generateRandomNumbers();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sudokuBoard.setCell(row + i, col + j, numbers[i * 3 + j]);
            }
        }
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

}
