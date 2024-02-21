import java.util.Random;

public class RandomGenerator {
    private static final int size = SudokuBoard.size;
    private Random random;

    public RandomGenerator() {
        this.random = new Random();
    }

    private void shuffleArray(Integer[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    public Integer[] generateRandomNumbers() {
        Integer[] numbers = new Integer[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = i + 1;
        }
        shuffleArray(numbers);
        return numbers;
    }


}
