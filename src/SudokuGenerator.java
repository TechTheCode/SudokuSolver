import java.util.Random;

public class SudokuGenerator {
    private SudokuBoard sudokuBoard;
    private static final int size = SudokuBoard.size; // size of the Sudoku board
    private int sqrtSize; // square root of size
    private int remove; // number of digits to remove
    private Random random; // random number generator
    private SudokuSolver solver;

    // Constructor
    public SudokuGenerator(int remove) {
        this.sqrtSize = (int) Math.sqrt(size);
        this.remove = remove;
        this.random = new Random();
        this.sudokuBoard = new SudokuBoard();
        this.solver = new SudokuSolver(this.sudokuBoard);
        generateSudoku();
    }

    // Generate Sudoku board
    private void generateSudoku() {
        fillDiagonal();
        if (!solver.sudokuSolve(0, 0)) {
            throw new RuntimeException("Failed to generate a sudoku board");
        }
        removeDigits();
    }

    // Fill diagonal blocks with unique numbers
    private void fillDiagonal() {
        for (int i = 0; i < size; i += sqrtSize) {
            fillBlock(i, i);
        }
    }

    // Fill a 3x3 box with unique numbers
    private void fillBlock(int row, int col) {
        for (int i = 0; i < sqrtSize; i++) {
            for (int j = 0; j < sqrtSize; j++) {
                int num;
                do {
                    num = random.nextInt(size) + 1;
                } while (!isSafe(row, col, num));
                sudokuBoard.setCell(row + i, col + j, num);
            }
        }
    }

    // Check if it's safe to place a number at given position
    private boolean isSafe(int row, int col, int num) {
        return !usedInRow(row, num) && !usedInColumn(col, num) && !usedInBox(row - row % sqrtSize, col - col % sqrtSize, num);
    }

    // Check if the number is already used in the row
    private boolean usedInRow(int row, int num) {
        for (int col = 0; col < size; col++) {
            if (sudokuBoard.getCell(row, col) == num) {
                return true;
            }
        }
        return false;
    }

    // Check if the number is already used in the column
    private boolean usedInColumn(int col, int num) {
        for (int row = 0; row < size; row++) {
            if (sudokuBoard.getCell(row, col) == num) {
                return true;
            }
        }
        return false;
    }

    // Check if the number is already used in the 3x3 box
    private boolean usedInBox(int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < sqrtSize; row++) {
            for (int col = 0; col < sqrtSize; col++) {
                if (sudokuBoard.getCell(boxStartRow + row, boxStartCol + col) == num) {
                    return true;
                }
            }
        }
        return false;
    }

    // Remove digits to make a puzzle
    private void removeDigits() {
        int count = remove;
        while (count != 0) {
            int cellId = random.nextInt(size * size);
            int row = cellId / size;
            int col = cellId % size;
            if (sudokuBoard.getCell(row, col) != 0) {
                sudokuBoard.setCell(row, col, 0);
                count--;
            }
        }
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }
}
