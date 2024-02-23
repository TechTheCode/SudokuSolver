public class Main {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();

        //
        SudokuGenerator generator = new SudokuGenerator(30);
        SudokuBoard randomBoard = generator.getSudokuBoard();
        SudokuSolver solveRandom = new SudokuSolver(randomBoard);

        SudokuGenerator generator2 = new SudokuGenerator(30);
        SudokuBoard randomBoard2 = generator2.getSudokuBoard();
        SudokuSolver solveRandom2 = new SudokuSolver(randomBoard2);

        SudokuGenerator generator3 = new SudokuGenerator(30);
        SudokuBoard randomBoard3 = generator3.getSudokuBoard();
        SudokuSolver solveRandom3 = new SudokuSolver(randomBoard3);

        SudokuGenerator generator4 = new SudokuGenerator(30);
        SudokuBoard randomBoard4 = generator4.getSudokuBoard();
        SudokuSolver solveRandom4 = new SudokuSolver(randomBoard4);
        //

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
                {6, 4, 2, 7, 0, 5, 1, 0, 3},
                {0, 0, 0, 0, 0, 0, 8, 5, 0},
                {0, 8, 0, 0, 4, 3, 0, 7, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 3, 2, 9, 6, 5},
                {0, 0, 6, 0, 0, 1, 3, 0, 0},
                {0, 6, 7, 3, 0, 0, 0, 8, 9},
                {1, 0, 0, 0, 2, 0, 0, 0, 6},
                {3, 0, 4, 9, 6, 8, 7, 0, 1}
        };

        int[][] solutionValues = {
                {6, 4, 2, 7, 8, 5, 1, 9, 3},
                {7, 1, 3, 2, 9, 6, 8, 5, 4},
                {5, 8, 9, 1, 4, 3, 6, 7, 2},
                {4, 3, 5, 6, 7, 9, 2, 1, 8},
                {8, 7, 1, 4, 3, 2, 9, 6, 5},
                {9, 2, 6, 8, 5, 1, 3, 4, 7},
                {2, 6, 7, 3, 1, 4, 5, 8, 9},
                {1, 9, 8, 5, 2, 7, 4, 3, 6},
                {3, 5, 4, 9, 6, 8, 7, 2, 1}
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

        System.out.println(); // Use when using logging on SudokuGenerator
        System.out.println("Board with predefined values:");
        board.printBoard();
        System.out.println();

        System.out.println("Board with unsolved values:");
        unsolvedBoard.printBoard();
        System.out.println();

        System.out.println("Random board generation:");
        randomBoard.printBoard();
        System.out.println();

        System.out.println("Solving unsolved values:");
        solveUnsolved.isUnique(0, 0);
        if (solveUnsolved.getSolutionCount() == 1) {
            System.out.println("The board has a unique solution");
            solveUnsolved.sudokuSolve(0, 0);
        } else if (solveUnsolved.getSolutionCount() > 1) {
            System.out.println("The board has multiple solutions");
        } else {
            System.out.println("The board has no solutions");
        }
        unsolvedBoard.printBoard();
        System.out.println();

        boolean isValid = SudokuValidator.isValidSudoku(board);
        System.out.println("The board is " + (isValid ? "valid" : "invalid"));
        System.out.println();

        boolean isRandomValid = SudokuValidator.isValidSudoku(randomBoard);
        System.out.println("The random board is " + (isRandomValid ? "valid" : "invalid"));
        System.out.println();

        boolean isUnsolvedValid = SudokuValidator.isValidSudoku(unsolvedBoard);
        System.out.println("The unsolved board is " + (isUnsolvedValid ? "valid" : "invalid"));
        System.out.println();

        boolean isSolutionValid = SudokuValidator.isValidSudoku(solutionBoard);
        System.out.println("The solution board is " + (isSolutionValid ? "valid" : "invalid"));
        System.out.println();

        String result = solutionBoard.equals(unsolvedBoard) ? "The solution matches solver"
                : "Solution does not match the sovler";
        System.out.println(result);
        System.out.println();


        //Test random board
        System.out.println("Solving random values:");
        solveRandom.isUnique(0, 0);
        if (solveRandom.getSolutionCount() == 1) {
            System.out.println("The board has a unique solution");
            solveRandom.sudokuSolve(0, 0);
        } else if (solveRandom.getSolutionCount() > 1) {
            System.out.println("The board has multiple solutions");
        } else {
            System.out.println("The board has no solutions");
        }
        randomBoard.printBoard();
        System.out.println();

        boolean isRandomValidAgain = SudokuValidator.isValidSudoku(randomBoard);
        System.out.println("The random board is " + (isRandomValidAgain ? "valid" : "invalid"));
        System.out.println();

        System.out.println("Solving random values:");
        solveRandom2.isUnique(0, 0);
        if (solveRandom2.getSolutionCount() == 1) {
            System.out.println("The board has a unique solution");
            solveRandom2.sudokuSolve(0, 0);
        } else if (solveRandom2.getSolutionCount() > 1) {
            System.out.println("The board has multiple solutions");
        } else {
            System.out.println("The board has no solutions");
        }
        randomBoard2.printBoard();
        System.out.println();

        boolean isRandomValidAgain2 = SudokuValidator.isValidSudoku(randomBoard2);
        System.out.println("The random board 2 is " + (isRandomValidAgain2 ? "valid" : "invalid"));
        System.out.println();
    }
}
