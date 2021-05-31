package personal.CP.CP_qstion;

import personal.aaa_shared.Reader;

import java.util.LinkedList;
import java.util.List;

/**
 * codechef id : CHARGES
 *
 *
 * There are N subatomic particles lined up in a row.
 * There are two types: protons and electrons.
 * Protons have a positive charge and are represented by 1, while electrons have a negative charge and are represented by 0.
 *
 * Our current understanding of physics gives us a way to predict how the particles will be spaced out,
 * if we know their charges.
 * Two adjacent particles will be separated by 1 unit if they have opposite charges,
 * and 2 units if they have the same charge.
 *
 *
 *
 * When Chef is not in the kitchen, he is doing physics experiments on subatomic particles.
 * He is testing the hypothesis by having N particles in a row, and he will change the charge of a particle K times.
 * In the i-th update, he will change the charge of the Qi-th particle.
 * After each update, find the distance between the first and last particle.
 *
 * Note: Each update is persistent for further updates
 */

/**
 *
 * https://www.youtube.com/watch?v=cnrTw27ddg4
 *
 *
 * find initial distance .
 *
 * and for each query , calculate the delta
 *
 */

public class cc3_charges {

    public static void main(String[] args) {
        Reader r = new Reader();
        int t = r.nextInt();
        Solution_3 s = new Solution_3();

        while (t-- > 0) {
            int n = r.nextInt();
            int k = r.nextInt();
            String str = r.nextLine();
            int[] arr = new int[k];
            for (int i = 0; i < k; i++) {
                arr[i] = r.nextInt();
            }

            List<Integer> ans = s.fun(n, k, str.toCharArray(), arr);
            for (int i : ans)
                System.out.println(i);
        }
    }
}


class Solution_3 {

    List<Integer> fun(int n, int k, char[] str, int[] arr) {

        int extreme_dist = get(n, k, str);

        List<Integer> ans = new LinkedList<>();

        for (int i : arr) {
            int idx = i - 1;

            char curr_before_change = str[idx];
            char curr_after_change = (curr_before_change == '0') ? '1' : '0';
            ;

            Character left_ngbr = (idx > 0) ? str[idx - 1] : null;
            Character right_ngbr = (idx + 1 < str.length) ? str[idx + 1] : null;
            int left_change = 0;
            int right_change = 0;

            if (left_ngbr != null) {
                boolean is_same = (curr_after_change == left_ngbr);
                left_change = (is_same) ? 1 : -1;
            }

            if (right_ngbr != null) {
                boolean is_same = (curr_after_change == right_ngbr);
                right_change = (is_same) ? 1 : -1;
            }

            extreme_dist = extreme_dist + left_change + right_change;
            ans.add(extreme_dist );

            //make change
            str[idx] = curr_after_change;


        }


        return ans;


    }

    int get(int n, int k, char[] str) {
        int[] arr = new int[n];
        arr[0] = 0;
        for (int i = 1; i < n; i++) {
            boolean is_same = str[i] == str[i - 1];
            int seperation = (is_same) ? 2 : 1;
            arr[i] = arr[i - 1] + seperation;
        }
        return arr[n - 1] - arr[0];
    }


}