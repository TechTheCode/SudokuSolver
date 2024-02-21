public class Main {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();
        SudokuGenerator generator = new SudokuGenerator();
        SudokuBoard randomBoard = generator.getSudokuBoard();
        SudokuBoard unsolvedBoard = new SudokuBoard();
        SudokuSolver solve = new SudokuSolver(unsolvedBoard);

        /*
        // Testing SudokuBoard

        // Print the empty board
        System.out.println("Empty Board:");
        board.printBoard();
        System.out.println();

        // Set some values in the board
        board.setCell(0, 0, 5);
        board.setCell(1, 1, 3);
        board.setCell(2, 2, 1);

        // Print the board with some values set
        System.out.println("Board after setting some values:");
        board.printBoard();
        System.out.println();
        */

        // Testing SudokuValidator
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

        int[][] unsolvedValues ={
                {1, 0, 0, 0, 2, 0, 3, 0, 4},
                {0, 4, 0, 5, 6, 0, 7, 8, 0},
                {0, 9, 0, 0, 8, 0, 0, 0, 2},
                {0, 0, 3, 0, 0, 8, 0, 0, 7},
                {0, 0, 7, 0, 0, 0, 6, 0, 0},
                {8, 0, 0, 2, 0, 0, 9, 0, 0},
                {6, 0, 0, 0, 1, 0, 0, 3, 0},
                {0, 5, 8, 0, 9, 3, 0, 7, 0},
                {2, 0, 1, 0, 4, 0, 0, 0, 6}
        };


        // Set board to predefined values
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

        if (!solve.sudokuSolve(0, 0)) {
            throw new RuntimeException("Failed to generate a sudoku board");
        }
        unsolvedBoard.printBoard();
        System.out.println();

        boolean isUnsolvedValid = SudokuValidator.isValidSudoku(unsolvedBoard);
        System.out.println("The board is " + (isUnsolvedValid ? "valid" : "invalid"));
        System.out.println();
    }
}
