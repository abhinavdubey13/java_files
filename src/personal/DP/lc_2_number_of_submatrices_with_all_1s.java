package personal.DP;


/**
 * leetcode id : 1277
 *
 *
 * Given a m * n matrix of ones and zeros, return how many square sub matrices have all ones.
 */


/**
 *
 * dp[i][j] means the size of biggest square with A[i][j] as bottom-right corner.
 * dp[i][j] also means the number of squares with A[i][j] as bottom-right corner.
 *
 * If A[i][j] == 0, no possible square.
 *
 * If A[i][j] == 1, then
 * we compare the size of square dp[i-1][j-1], dp[i-1][j] and dp[i][j-1].
 * min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1 is the maximum size of square that we can find.
 *
 *
 * "Your current element is only as strong as your weakest neighbour"
 *
 * ==============
 * TC =  R*C
 * SC = 1 (we can wuse the matric provided in input)
 * ==============
 */


public class lc_2_number_of_submatrices_with_all_1s {
}


class Solution2 {

    public int function(int[][] mat) {
        int answer = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {

                if (mat[i][j] == 1) {
                    int left = (j - 1 >= 0) ? mat[i][j - 1] : 0;
                    int top = (i - 1 >= 0) ? mat[i - 1][j] : 0;
                    int diag = (i - 1 >= 0 && j - 1 >= 0) ? mat[i - 1][j - 1] : 0;
                    mat[i][j] = 1 + Math.min(left, Math.min(top, diag));
                }
                answer += mat[i][j];
            }
        }
        return answer;
    }
}