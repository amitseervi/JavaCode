import java.util.Arrays;

// https://www.geeksforgeeks.org/length-of-smallest-subsequence-such-that-sum-of-elements-is-greater-than-equal-to-k/
public class SmallestSubSeq {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 5, 6, 3, 7, 9, 14, 10, 2, 5};
        int k = 35;
        Arrays.sort(arr);
        int sum = 0;
        int index = arr.length - 1;
        while (sum < k && index >= 0) {
            System.out.println("Pick " + arr[index]);
            sum += arr[index--];
        }
        System.out.println(arr.length - 1 - index);
    }
}
