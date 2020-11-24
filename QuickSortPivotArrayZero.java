import javax.print.attribute.standard.Media;
import java.util.LinkedList;
import java.util.Queue;

public class QuickSortPivotArrayZero {

    public int[] sort(int[] arr) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, arr.length - 1});
        while (queue.size() > 0) {
            int[] indexes = queue.remove();
            int leftIndex = indexes[0];
            int rightIndex = indexes[1];

            int index = partition(arr, leftIndex, rightIndex);
            if (leftIndex < index - 1) {
                queue.add(new int[]{leftIndex, index - 1});
            }
            if (index < rightIndex) {
                queue.add(new int[]{index, rightIndex});
            }
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        int i = left;
        int j = right;
        int pivotVal = arr[left];
        while (i <= j) {
            while (arr[i] < pivotVal) {
                i++;
            }
            while (arr[j] > pivotVal) {
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
}
