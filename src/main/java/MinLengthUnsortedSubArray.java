//https://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/amp/
public class MinLengthUnsortedSubArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        findMinLengthUnsortedSubArr(arr);
    }

    public static void findMinLengthUnsortedSubArr(int[] arr) {
        int candidateStart = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                candidateStart = i - 1;
                break;
            }
        }
        if (candidateStart == -1) {
            System.out.println("Array is already sorted");
            return;
        }

        int candidateEnd = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i + 1] < arr[i]) {
                candidateEnd = i + 1;
                break;
            }
        }
        int minIndex = candidateStart;
        int maxIndex = candidateStart;
        for (int i = candidateStart + 1; i <= candidateEnd; i++) {
            if (arr[minIndex] > arr[i]) {
                minIndex = i;
            }
            if (arr[maxIndex] < arr[i]) {
                maxIndex = i;
            }
        }
        int actualMinIndex = 0;
        int actualMaxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (minIndex != i && arr[minIndex] > arr[i]) {
                actualMinIndex++;
            }

            if (maxIndex != i && arr[maxIndex] > arr[i]) {
                actualMaxIndex++;
            }
        }
        int subArrayStart = Math.min(candidateStart, actualMinIndex);
        int subArrayEnd = Math.max(candidateEnd, actualMaxIndex);
        System.out.println(subArrayStart + " " + subArrayEnd);
    }
}
