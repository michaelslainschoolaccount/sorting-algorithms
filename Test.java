import java.util.*;
import java.io.*;

public class Test {
    private final static int maxLength = 100000;
    private final static int increment = 10000;
    private static long[][] times = new long[3][(maxLength / increment) + 1];

    public static void run() {
        for (int n = 0; n < 1000; n++) {
            for (int i = 0; i < 3; i++) {
                for (int length = 0; length < maxLength + 1; length += increment) {
                    SortedArray arr = new SortedArray(randomArray(length == 0 ? 1 : length));

                    long startTime = System.nanoTime();

                    switch (i) {
                        case 0:
                            arr.quickSort();
                            break;
                        case 1:
                            arr.heapSort();
                            break;
                        case 2:
                            arr.radixSort();
                            break;
                    }

                    long endTime = System.nanoTime();
                    long elapsedTime = endTime - startTime;
                    long savedTime = times[i][(int) (length / increment)];

                    if (savedTime > 0) {
                        times[i][(int) (length / increment)] = (elapsedTime + times[i][(int) (length / increment)]) / 2;
                    } else {
                        times[i][(int) (length / increment)] = elapsedTime;
                    }

                }
            }
        }
    }

    private static int[] randomArray(int length) {
        Random random = new Random();
        int[] arr = new int[length];

        for (int i = 0; i < length; i++)
            arr[i] = random.nextInt(length);

        return arr;
    }

    public static void print() {
        try {
            FileWriter writer = new FileWriter("output.txt");
            for (int i = 0; i < times.length; i++) {
                writer.write("\n\n" + (i == 0 ? "Quick Sort" : i == 1 ? "Heap Sort" : "Radix Sort") + "\n[ ");

                for (int j = 0; j < times[i].length; j++) {
                    long time = times[i][j];
                    writer.write(time + ", ");
                }

                writer.write("]");
            }
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
