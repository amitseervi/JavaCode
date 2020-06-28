// https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
public class KMP {
    public static void main(String[] args) {
        KMP("ABABDABACDABABCABAB", "ABABCABAB");
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void KMP(String text, String pattern) {
        int[] LPS = computeLPSArray(pattern.toCharArray());
        printArray(LPS);
        int i = 0, j = 0;
        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }

            if (j == pattern.length()) {
                System.out.println("Found pattern at " + (i - j));
                j = LPS[j - 1];
            } else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = LPS[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    public static int[] computeLPSArray(char[] pattern) {
        int[] LPS = new int[pattern.length];
        int len = 0;
        LPS[0] = 0;
        int i = 1;
        while (i < pattern.length) {
            if (pattern[i] == pattern[len]) {
                LPS[i++] = ++len;
            } else {
                if (len != 0) {
                    len = LPS[len - 1];
                } else {
                    LPS[i++] = 0;
                }
            }
        }
        return LPS;
    }
}
