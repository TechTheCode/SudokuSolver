public class Main {
    public static void main(String[] args) {

        // Create a new Sudoku board
        SudokuBoard board = new SudokuBoard();

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

    }
}
