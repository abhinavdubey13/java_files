package personal.CP;

/**
 *
 */

/**
 *
 * cache the solution using map
 *
 */


public class tt3 {

    public static void main(String[] args) {

        Reader sc = new Reader();
        int t = sc.nextInt();
        tt3_soln solution = new tt3_soln();
        while (t-- > 0) {
            //taking row and col inp
            int g = sc.nextInt();
            int p = sc.nextInt();
            int[] arr = new int[10];

            for (int i = 0; i < 10; i++) {
                arr[i] = sc.nextInt();
            }


            int[] ans = solution.function(g - 1, p, arr);
            System.out.println(ans[0] + " " + ans[1]);
        }


    }
}


class tt3_soln {


    // g : 0-9
    int[] function(int g, int p, int[] arr) {

        if (g == 9) {
            int max = (arr[9] <= p) ? 1 : (int) Math.ceil((double) arr[9] / p);
            return new int[]{1, max};
        }

        int ppl = 0;
        for (int i = 9; i > g; i--) {
            ppl += arr[i];
        }

        int min_hlpr = (int) Math.ceil((double) ppl / p);
        int min = (ppl % p == 0) ? min_hlpr + 1 : min_hlpr;
        int x = ppl + arr[g];
        int max = (int) Math.ceil((double) x / p);
        return new int[]{min, max};

    }


}
