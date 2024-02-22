public class SudokuSolver {
    private SudokuBoard sudokuBoard;
    private static final int size = SudokuBoard.size;
    private int solutionCount = 0;
    private boolean solutionFound = false;

    public SudokuSolver(SudokuBoard board) {
        this.sudokuBoard = board;
    }

    boolean sudokuSolve(int row, int col) {
        if (solutionFound) { // Check if a solution is already found
            return false;
        }

        if (col == size) {
            col = 0;
            row++;
            if (row == size) {
                solutionCount++; // Increment solution count
                solutionFound = true; // Indicate that a solution is found
                return true; // Continue solving if less than two solutions found
            }
        }

        if (sudokuBoard.getCell(row, col) != 0) {
            return sudokuSolve(row, col + 1);
        }

        for (int num = 1; num <= size; num++) {
            if (isSafe(row, col, num)) {
                sudokuBoard.setCell(row, col, num);
                if (sudokuSolve(row, col + 1)) {
                    return true; // A solution has been found and the board is filled
                }
                if (!solutionFound) {
                    sudokuBoard.setCell(row, col, 0); // Reset cell for backtracking only if no solution found
                }
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

    public int getSolutionCount() {
        return solutionCount;
    }
}
