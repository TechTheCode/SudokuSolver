public class Main {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard randomBoard = generator.getSudokuBoard();
        SudokuBoard unsolvedBoard = new SudokuBoard();
        SudokuSolver solveUnsolved = new SudokuSolver(unsolvedBoard);
        SudokuBoard solutionBoard = new SudokuBoard();

        int[][] predefinedValues = {
                {7, 2, 3, 4, 6, 1, 5, 8, 9},
                {5, 1, 4, 9, 7, 8, 6, 3, 2},
                {8, 6, 9, 3, 5, 2, 1, 7, 4},
                {6, 3, 2, 7, 1, 9, 4, 5, 8},
                {9, 4, 5, 6, 8, 3, 2, 1, 7},
                {1, 8, 7, 5, 2, 4, 9, 6, 3},
                {4, 7, 6, 2, 3, 5, 8, 9, 1},
                {3, 9, 8, 1, 4, 6, 7, 2, 5},
                {2, 5, 1, 8, 9, 7, 3, 4, 6}
        };

        int[][] unsolvedValues = {
                {0, 3, 9, 0, 0, 0, 1, 2, 0},
                {0, 0, 0, 9, 0, 7, 0, 0, 0},
                {8, 0, 0, 4, 0, 1, 0, 0, 6},
                {0, 4, 2, 0, 0, 0, 7, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 9, 1, 0, 0, 0, 5, 4, 0},
                {5, 0, 0, 1, 0, 9, 0, 0, 3},
                {0, 0, 0, 8, 0, 5, 0, 0, 0},
                {0, 1, 4, 0, 0, 0, 8, 7, 0}
        };

        int[][] solutionValues = {
                {4, 3, 9, 6, 5, 8, 1, 2, 7},
                {1, 5, 6, 9, 2, 7, 3, 8, 4},
                {8, 2, 7, 4, 3, 1, 9, 5, 6},
                {6, 4, 2, 5, 1, 3, 7, 9, 8},
                {7, 8, 5, 2, 9, 4, 6, 3, 1},
                {3, 9, 1, 7, 8, 6, 5, 4, 2},
                {5, 7, 8, 1, 4, 9, 2, 6, 3},
                {2, 6, 3, 8, 7, 5, 4, 1, 9},
                {9, 1, 4, 3, 6, 2, 8, 7, 5}
        };

        for (int row = 0; row < SudokuBoard.size; row++) {
            for (int column = 0; column < SudokuBoard.size; column++) {
                board.setCell(row, column, predefinedValues[row][column]);
            }
        }

        for (int row = 0; row < SudokuBoard.size; row++) {
            for (int column = 0; column < SudokuBoard.size; column++) {
                unsolvedBoard.setCell(row, column, unsolvedValues[row][column]);
            }
        }

        for (int row = 0; row < SudokuBoard.size; row++) {
            for (int column = 0; column < SudokuBoard.size; column++) {
                solutionBoard.setCell(row, column, solutionValues[row][column]);
            }
        }

        System.out.println("Board with predefined values:");
        board.printBoard();
        System.out.println();

        System.out.println("Board with unsolved values:");
        unsolvedBoard.printBoard();
        System.out.println();

        boolean isValid = SudokuValidator.isValidSudoku(board);
        System.out.println("The board is " + (isValid ? "valid" : "invalid"));
        System.out.println();

        System.out.println("Random board generation:");
        randomBoard.printBoard();
        System.out.println();

        boolean isRandomValid = SudokuValidator.isValidSudoku(randomBoard);
        System.out.println("The random board is " + (isRandomValid ? "valid" : "invalid"));
        System.out.println();

        solveUnsolved.sudokuSolve(0, 0);
        if (solveUnsolved.getSolutionCount() == 1) {
            System.out.println("The puzzle has a unique solution.");
        } else if (solveUnsolved.getSolutionCount() > 1) {
            System.out.println("The puzzle has multiple solutions.");
        } else {
            System.out.println("The puzzle has no solutions.");
        }

        System.out.println();
        unsolvedBoard.printBoard();
        System.out.println();

        boolean isUnsolvedValid = SudokuValidator.isValidSudoku(unsolvedBoard);
        System.out.println("The unsolved board is " + (isUnsolvedValid ? "valid" : "invalid"));
        System.out.println();

        boolean isSolutionValid = SudokuValidator.isValidSudoku(solutionBoard);
        System.out.println("The solution board is " + (isSolutionValid ? "valid" : "invalid"));
        System.out.println();

        String result = solutionBoard.equals(unsolvedBoard) ? "The solution matches solver"
                : "Solution does not match the solver";
        System.out.println(result);

    }
}
