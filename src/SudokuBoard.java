public class SudokuBoard {

    public static final int size = 9;
    private int[][] board;

    public SudokuBoard(){
        board = new int[size][size];
    }

    public void  printBoard() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                System.out.print(board[row][column]+" ");// whitespace for neatness
            }
            System.out.println();
        }
    }

    public void setCell(int row, int column, int value) {
        board[row][column] = value;
    }

    public int getCell(int row, int column) {
        return board[row][column];
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SudokuBoard other = (SudokuBoard) obj;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (this.getCell(row, col) != other.getCell(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

}