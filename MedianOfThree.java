import javax.print.attribute.standard.Media;
import java.util.LinkedList;
import java.util.Queue;

public class MedianOfThree {

    public int[] sort(int[] arr) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, arr.length - 1});
        while (queue.size() > 0) {
            int[] indexes = queue.remove();
            int left = indexes[0];
            int right = indexes[1];

            int index = partition(arr, left, right);
            if (left < index - 1) {
                queue.add(new int[]{left, index - 1});
            }
            if (index < right) {
                queue.add(new int[]{index, right});
            }
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        int i = left;
        int j = right;
        int pivot = arr[(left + right) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void print(int[] a, int[] arr) {
        int[] array = a == null ? arr : a;
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
    }
}
