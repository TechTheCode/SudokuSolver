public class Main {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();

        /*
        // Testing SudokuBoard class

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

        // Set board to predefined values
        for (int row = 0; row < SudokuBoard.size; row++) {
            for (int column = 0; column < SudokuBoard.size; column++) {
                board.setCell(row, column, predefinedValues[row][column]);
            }
        }

        System.out.println("Board with predefined values:");
        board.printBoard();
        System.out.println();

        System.out.println("Row check:");
        for (int row = 0; row < SudokuBoard.size; row++) {
            boolean isRowValid = SudokuValidator.isRowValid(board, row);
            System.out.println("Row " + (row + 1) + " is " + (isRowValid ? "valid" : "invalid"));
        }
        System.out.println();

        System.out.println("Column check:");
        for (int col = 0; col < SudokuBoard.size; col++) {
            boolean isColValid = SudokuValidator.isColumnValid(board, col);
            System.out.println("Column " + (col + 1) + " is " + (isColValid ? "valid" : "invalid"));
        }
        System.out.println();

        System.out.println("3x3 block check:");
        for (int row = 0; row < SudokuBoard.size; row += 3) {
            for (int column = 0; column < SudokuBoard.size; column += 3) {
                boolean isBoxValid = SudokuValidator.isBlockValid(board, row, column);
                System.out.println("Box starting at (" + row + "," + column + ") is "
                        + (isBoxValid ? "valid" : "invalid"));
            }
        }
        System.out.println();
    }
}
