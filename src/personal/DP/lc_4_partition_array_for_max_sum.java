package personal.DP;

public class lc_4_partition_array_for_max_sum {

    public static void main(String[] args) {
        int[] arr = {1, 15, 7, 9, 2, 5, 10};
        int R = 3;
        int ans = new Solution4().maxSumAfterPartitioning(arr, R);
        System.out.println(ans);
    }
}


class Solution4 {

    int[] dp;

    public int maxSumAfterPartitioning(int[] arr, int R) {
        int n = arr.length;
        dp = new int[n];
        return dfs(0, arr, R, n);
    }

    int dfs(int idx_to_fill, int[] arr, int R, int n) {

        //filled all in dp[]
        if (idx_to_fill >= n) {
            return 0;
        }

        //already calculated value
        if (dp[idx_to_fill] != 0) {
            return dp[idx_to_fill];
        }

        int max_sum = 0;
        int max_ele = 0;

        for (int i = idx_to_fill; i < Math.min(n, i + R); i++) {
            max_ele = Math.max(max_ele, arr[i]);

            int num_of_elements = i - idx_to_fill + 1;
            max_sum = Math.max(max_sum, max_ele * num_of_elements + dfs(i + 1, arr, R, n));
        }


        dp[idx_to_fill] = max_sum;
        return dp[idx_to_fill];


    }


}