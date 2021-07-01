package personal.IE;

public class a {

    public static void main(String[] args) {

        int v = 1;

        v = v + v++;

//        v++;
//        v+=v;

        System.out.println(v);

    }
}


class Solution {

    int function(int[] arr, int k) {

        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];

        for (int i = 1; i <= k; i++) {
            dp[i] = Math.min(arr[i], dp[i - 1]);
        }

        for (int i = k + 1; i < n; i++) {
            int incl = (i - k - 1 >= 0) ? 1 + dp[i - k - 1] : n;
            int excl = n;

            for (int j = i - 1; j >= 0 && j >= i - k; j--) {
                if (arr[i] == 1) {
                    excl = Math.min(excl, dp[j]);
                }
            }

            dp[i] = Math.min(incl, excl);
        }


        return dp[n - 1];

    }
}
