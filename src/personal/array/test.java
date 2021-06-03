package personal.array;

import java.util.Arrays;

public class test {

    public static void main(String[] args) {

        int arr[] = { 900, 940, 950, 1100, 1500, 1800 };
        int dep[] = { 910, 1200, 1120, 1130, 1900, 2000 };
        int answer = new Solution_1().function(arr, dep);

        System.out.println(answer);

    }
}


class Solution_1 {


    int function(int[] a, int[] d) {


        int n = a.length;
        int ans = 1;
        int curr = 1;
        int i = 1;
        int j = 0;

        Arrays.sort(a);
        Arrays.sort(d);


        while (i < n && j < n) {

            while (i < n && j < n && a[i] > d[j]) {
                j++;
                curr--;

                if (curr <= 0) {
                    curr = 0;
                }
            }

            while (i < n && j < n && a[i] <= d[j]) {
                i++;
                curr++;
                ans = Math.max(ans, curr);

            }

        }

        return  ans;

    }

}