public class MergeSort {
    /* Iterative mergesort function to sor
t arr[0...n-1] */
    static int[] mergeSort(int[] arr, int n) {


        int currSize;

        int leftStart;

        for (currSize = 1; currSize <= n - 1;
             currSize = 2 * currSize) {

            // Pick starting point of different
            // subarrays of current size
            for (leftStart = 0; leftStart < n - 1;
                 leftStart += 2 * currSize) {
                int mid = Math.min(leftStart + currSize - 1, n - 1);

                int rightEnd = Math.min(leftStart
                        + 2 * currSize - 1, n - 1);

                merge(arr, leftStart, mid, rightEnd);
            }
        }
        return arr;
    }

    static void merge(int arr[], int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
