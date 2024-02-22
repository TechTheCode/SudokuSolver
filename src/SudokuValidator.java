import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SudokuValidator {
    private static final int size = SudokuBoard.size;

    static boolean isRowValid(SudokuBoard board, int row) {    // Change public back to private after testing
        System.out.println("Validating row " + row + " in thread " + Thread.currentThread().getName());
        boolean[] seen = new boolean[size + 1];
        for (int col = 0; col < size; col++) {
            int cell = board.getCell(row, col);
            if (cell != 0) {
                if (seen[cell]) {
                    return false;
                }
                seen[cell] = true;
            }
        }
        return true;
    }

    static boolean isColumnValid(SudokuBoard board, int col) {   // Change public back to private after testing
        System.out.println("Validating column " + col + " in thread " + Thread.currentThread().getName());
        boolean[] seen = new boolean[size + 1];
        for (int row = 0; row < size; row++) {
            int num = board.getCell(row, col);
            if (num != 0) {
                if (seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
        }
        return true;
    }

    static boolean isBlockValid(SudokuBoard board, int startRow, int startCol) {   // Change public back to private after testing
        System.out.println("Validating block starting at (" + startRow + "," + startCol + ") in thread " + Thread.currentThread().getName());
        boolean[] seen = new boolean[size + 1];
        // check for 3x3 block
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int num = board.getCell(startRow + row, startCol + col);
                if (num != 0) {
                    if (seen[num]) {
                        return false;
                    }
                    seen[num] = true;
                }
            }
        }
        return true;
    }
    /*
    public static boolean isValidSudoku(SudokuBoard board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j) == 0) {
                    // Return false if any cell is zero
                    return false;
                }
            }
            if (!isRowValid(board, i) || !isColumnValid(board, i)
                    || !isBlockValid(board, i - i % 3, i - i % 3)) {
                return false;
            }

        }
        return true;
    }

     */
    public static boolean isValidSudoku(SudokuBoard board) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Boolean>> futures = new ArrayList<>();

        // Submit row, column, and block checks to the executor
        for (int i = 0; i < size; i++) {
            final int index = i;
            futures.add(executor.submit(() -> isRowValid(board, index)));
            futures.add(executor.submit(() -> isColumnValid(board, index)));
            futures.add(executor.submit(() -> isBlockValid(board, index - index % 3, index - index % 3)));
        }

        // Shutdown executor and wait for tasks to complete
        executor.shutdown();

        // Check results
        try {
            for (Future<Boolean> future : futures) {
                if (!future.get()) {
                    return false; // One of the checks failed
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true; // All checks passed
    }

}
