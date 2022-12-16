import java.util.*;

public class SortedArray {
    int[] nums;

    public SortedArray() {
        final int[] defaultNums = new int[] { 0, 2, 4, 3 };
        nums = defaultNums;
    }

    public SortedArray(int[] nums) {
        this.nums = new int[nums.length];

        for (int i = 0; i < nums.length; i++)
            this.nums[i] = nums[i];
    }

    public SortedArray quickSort() {
        quickSort(nums, 0, nums.length - 1);
        return this;
    }

    private void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        int i = low;
        int j = high;

        while (i <= j) {
            while (arr[i] < pivot)
                i++;

            while (arr[j] > pivot)
                j--;

            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(arr, low, j);
        if (high > i)
            quickSort(arr, i, high);
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public SortedArray heapSort() {
        int length = nums.length;

        for (int i = length / 2 - 1; i >= 0; i--)
            heapSort(nums, length, i);

        for (int i = length - 1; i >= 0; i--) {
            int x = nums[0];
            nums[0] = nums[i];
            nums[i] = x;

            heapSort(nums, i, 0);
        }

        return this;
    }

    private void heapSort(int[] arr, int heapSize, int index) {
        int largest = index;
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;

        if (leftChildIndex < heapSize && arr[leftChildIndex] > arr[largest])
            largest = leftChildIndex;

        if (rightChildIndex < heapSize && arr[rightChildIndex] > arr[largest])
            largest = rightChildIndex;

        if (largest != index) {
            int swap = arr[index];
            arr[index] = arr[largest];
            arr[largest] = swap;

            heapSort(arr, heapSize, largest);
        }
    }

    public SortedArray radixSort() {
        radixSort(nums, nums.length);
        return this;
    }

    private void radixSort(int[] arr, int length) {
        int max = arr[0];
        for (int i = 1; i < length; i++)
            if (arr[i] > max)
                max = arr[i];

        for (int exp = 1; max / exp > 0; exp *= 10)
            radixSort(arr, length, exp);
    }

    private void radixSort(int[] arr, int length, int exp) {
        int output[] = new int[length];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < length; i++)
            count[(arr[i] / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = length - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (i = 0; i < length; i++)
            arr[i] = output[i];
    }

    public String toString() {
        String out = "[ ";

        for (int num : nums)
            out += num + ", ";

        out += "]";
        return out;
    }
}
