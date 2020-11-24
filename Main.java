import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int iterations;
    private static int numbers;
    static int[] sorted = new int[numbers];
    static Instant start;
    private static ArrayList<int[]> allUnsortedArrays = new ArrayList<>();
    static boolean runAll = false;
    private static int[] basicArrayUsed = new int[numbers];


    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.print("Iterations = ");
        iterations = input.nextInt();
        System.out.print("N = ");
        numbers = input.nextInt();
        System.out.println("Reading in random arrays...");
        basicArray(numbers);
        setAllUnsortedArrays();
        menu();
    }

    private static void finish(String algorithm) throws FileNotFoundException, InterruptedException {
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        double average = (double) (timeElapsed) / (iterations);
        System.out.println("*----------- " + algorithm + " -----------*");
        System.out.println("Iterations: " + iterations + "         Numbers:" + numbers);
        System.out.println("Average: " + average + " ms        Total time:" + timeElapsed + " ms");
        if (!runAll)
            menu();
    }

    public static void menu() throws FileNotFoundException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*--------------------------- Select algorithm ----------------------------*");
        System.out.println("|  1. QuickSort - Median Of Three Pivot       2. QuickSort - Random Pivot |");
        System.out.println("|  3. Quicksort - Pivot = array[0]            4. Insertion-Sort           |");
        System.out.println("|  5. Merge-Sort                              6. Binary-Search            |");
        System.out.println("|  7. Change iterations and numbers           8. Run all                  |");
        System.out.println("*-------------------------------------------------------------------------*");
        System.out.print("> ");
        int algorithm = scanner.nextInt();
        switch (algorithm) {
            case (1):
                medianOfThree();
                break;
            case (2):
                randomPivot();
                break;
            case (3):
                quickSortArrayZero();
                break;
            case (4):
                insertionSort();
                break;
            case (5):
                mergeSort();
                break;
            case (6):
                binarySearch();
                break;
            case (7):
                System.out.print("Iterations: ");
                iterations = scanner.nextInt();
                System.out.print("Numbers: ");
                numbers = scanner.nextInt();
                menu();
            case (8):
                runAll = true;
                medianOfThree();
                randomPivot();
                quickSortArrayZero();
                insertionSort();
                mergeSort();
                binarySearch();
                break;
        }
    }

    private static void medianOfThree() throws FileNotFoundException, InterruptedException {
        MedianOfThree medianOfThree = new MedianOfThree();
        start = Instant.now();
        for (int i = 0; i < iterations; i++) {
            sorted = medianOfThree.sort(allUnsortedArrays.get(i));
        }
        finish("Median Of Three");
    }

    private static void randomPivot() throws FileNotFoundException, InterruptedException {
        QuickSortRandomPivot quickSortRandomPivot = new QuickSortRandomPivot();
        start = Instant.now();
        for (int i = 0; i < iterations; i++) {
            sorted = quickSortRandomPivot.sort(allUnsortedArrays.get(i));
        }
        finish("Quick Sort");
    }

    private static void quickSortArrayZero() throws FileNotFoundException, InterruptedException {
        QuickSortPivotArrayZero quickSortPivotArrayZero = new QuickSortPivotArrayZero();
        start = Instant.now();
        for (int i = 0; i < iterations; i++) {
            sorted = quickSortPivotArrayZero.sort(allUnsortedArrays.get(i));
        }
        finish("Quick Sort Pivot Array[0]");
    }

    private static void insertionSort() throws FileNotFoundException, InterruptedException {
        if (!(numbers > 9800)) {
            InsertionSort insertionSort = new InsertionSort();
            start = Instant.now();
            for (int i = 0; i < iterations; i++) {
                sorted = insertionSort.insertionSortRecursive(allUnsortedArrays.get(i), allUnsortedArrays.get(i).length);
            }
            finish("Insertion Sort");
        }
    }

    private static void mergeSort() throws FileNotFoundException, InterruptedException {
        MergeSort mergeSort = new MergeSort();
        start = Instant.now();
        for (int i = 0; i < iterations; i++) {
            sorted = mergeSort.mergeSort(allUnsortedArrays.get(i), allUnsortedArrays.get(i).length);
        }
        finish("Merge Sort");
    }

    private static void binarySearch() throws FileNotFoundException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number to find: ");
        int numberToFind = scanner.nextInt();
        MergeSort mergeSort1 = new MergeSort();
        BinarySearch binarySearch = new BinarySearch();
        boolean result = false;
        start = Instant.now();
        for (int s = 0; s < iterations; s++) {
            sorted = mergeSort1.mergeSort(allUnsortedArrays.get(s), allUnsortedArrays.get(s).length);
            result = binarySearch.binarySearch(sorted, 0, sorted.length - 1, numberToFind);
            if (!result)
                result = false;
            else {
                result = true;
                break;
            }
        }
        if (result)
            System.out.println("true");
        else
            System.out.println("false");
        runAll = false;
        finish("Binary Search");
    }

    public static ArrayList<int[]> getAllUnsortedArrays() {
        return allUnsortedArrays;
    }

    public static void setAllUnsortedArrays() throws FileNotFoundException {
        for (int i = 0; i < iterations; i++) {
            int[] temp = readFromFile(numbers);
            allUnsortedArrays.add(temp);
        }
    }

    //          Reads numbers from the file but not in random order
    /*
    static int[] readFromFile(int numbers) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/RandomNumbers.txt"));
        int[] nums = new int[numbers];
        int i = 0;
        while (i < numbers) {
            nums[i] = scanner.nextInt();
            i++;
        }
        return nums;
    }
     */

    public static int[] getBasicArrayUsed() {
        return basicArrayUsed;
    }

    static int[] readFromFile(int numbers) throws FileNotFoundException {

        int[] arr = new int[numbers];
        Random random = new Random();
        int i = 0;
        while (i < numbers) {
            int num = random.nextInt(numbers);
            arr[i] = getBasicArrayUsed()[num];
            i++;
        }
        return arr;
    }


    static void basicArray(int numbers) {
        int[] arr = new int[numbers];
        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/RandomNumbers.txt"))) {
            int i = 0;
            while (i < numbers) {
                arr[i] = Integer.parseInt(br.readLine());
                i++;
            }

        } catch (IOException ignored) {
        }
        basicArrayUsed = arr;
    }
}

        /*
        Scanner scanner = new Scanner(new File("src/RandomNumbers.txt"));
        int[] nums = new int[numbers];
        int s = 0;
        Random rand = new Random();
        while (i < numbers) {
            int randomNumbers = rand.nextInt(numbers);
            nums[randomNumbers] = scanner.nextInt();
            s++;
        }
        return arr;
         */