import java.util.Random;

public class SudokuGenerator {
    private SudokuBoard sudokuBoard;
    private Random random;
    private static final int size = SudokuBoard.size;
    RandomGenerator rng = new RandomGenerator();
    private static final int remove = 30;
    private SudokuSolver solver;

    public SudokuGenerator() {
        this.sudokuBoard = new SudokuBoard();
        this.random = new Random();
        this.solver = new SudokuSolver(this.sudokuBoard); // Initialize the solver here
        fillDiagonalBlocks();
        if (!solver.sudokuSolve(0, 0)) {
            throw new RuntimeException("Failed to generate a sudoku board");
        }
        while (!generateUniqueSudoku()) {
            this.sudokuBoard.resetBoard(); // Reset board if not unique
            fillDiagonalBlocks();
            solver.sudokuSolve(0, 0);
        }
    }

    private boolean generateUniqueSudoku() {
        for (int i = 0; i < remove; ) {
            int row = random.nextInt(SudokuBoard.size);
            int col = random.nextInt(SudokuBoard.size);
            if (sudokuBoard.getCell(row, col) != 0) {
                sudokuBoard.setCell(row, col, 0);
                i++;
            }
        }
        solver.setSolutionCount(0);
        return solver.isUnique(0, 0) && solver.getSolutionCount() == 1;
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