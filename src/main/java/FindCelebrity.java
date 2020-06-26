//https://www.geeksforgeeks.org/the-celebrity-problem/

import java.util.Arrays;

public class FindCelebrity {
    public static int getCelebrity(int[][] matrix) {
        int n = matrix.length;
        int[][] flow = new int[2][n];
        Arrays.fill(flow[0], 0);
        Arrays.fill(flow[1], 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                flow[0][i] += matrix[i][j];
                flow[1][j] += matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            if (flow[0][i] == 0 && flow[1][i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

    public static int getCelebrityRec(int[][] matrix, int n) {
        if (n == 0) {
            return 0;
        }
        int childCelebrity = getCelebrityRec(matrix, n - 1);
        if (childCelebrity == -1) {
            return n;
        }
        //Child celebrity knows n and n does not know childCelebrity then n is new celebrity now
        if (matrix[childCelebrity][n] == 1 && matrix[n][childCelebrity] != 1) {
            return n;
        } else if (matrix[n][childCelebrity] == 1 && matrix[childCelebrity][n] != 1) {
            return childCelebrity;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}, {0, 0, 1, 0}};
        System.out.println(getCelebrityRec(matrix, matrix.length - 1));
    }
}
