public class SudokuSolver {
    private SudokuBoard sudokuBoard;
    private static final int size = SudokuBoard.size;
    private int solutionCount = 0;

    public SudokuSolver(SudokuBoard board) {
        this.sudokuBoard = board;
    }

    boolean isUnique(int row, int col) {
        if (col == size) {
            col = 0;
            row++;
            if (row == size) {
                solutionCount++; // Increment solution count
                return solutionCount < 2; // Continue solving if less than two solutions found
            }
        }

        if (sudokuBoard.getCell(row, col) != 0) {
            return isUnique(row, col + 1);
        }

        for (int num = 1; num <= size; num++) {
            if (isSafe(row, col, num)) {
                sudokuBoard.setCell(row, col, num);
                if (isUnique(row, col + 1) && solutionCount > 1) {
                    return false; // More than one solution found, stop solving
                }
                sudokuBoard.setCell(row, col, 0); // Reset cell for backtracking
            }
        }
        return false; // Trigger backtracking
    }

    boolean sudokuSolve(int row, int col) {
        if (col == size) {
            col = 0;
            row++;

            if (row == size) {
                return true; // Sudoku solved
            }
        }

        // Skip filled cells
        if (sudokuBoard.getCell(row, col) != 0) {
            return sudokuSolve(row, col + 1);
        }

        for (int num = 1; num <= 9; num++) {
            if (isSafe(row, col, num)) {
                sudokuBoard.setCell(row, col, num);
                if (sudokuSolve(row, col + 1)) {
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

    public int getSolutionCount() {
        return solutionCount;
    }

}
