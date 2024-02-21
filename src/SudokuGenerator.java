import java.util.Random;

public class SudokuGenerator {
    private SudokuBoard sudokuBoard;
    private Random random;
    private static final int size = SudokuBoard.size;
    RandomGenerator rng = new RandomGenerator();

    public SudokuGenerator() {
        this.sudokuBoard = new SudokuBoard();
        SudokuSolver solve = new SudokuSolver(this.sudokuBoard);
        this.random = new Random();
        fillDiagonalBlocks();
        if (!solve.sudokuSolve(0, 0)) {
            throw new RuntimeException("Failed to generate a sudoku board");
        }
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
