package personal.segment_tree;

import java.util.*;
import java.lang.*;
import java.io.*;


/**
 *
 * You are given an array of N integers(Indexed at 1)
 *
 *
 * For the given array you have to answer some queries given later.The queries are of 2 types:
 *
 * 1)TYPE 1 -> 1 L R (where L and R are integers)
 *
 *
 * For this query you have to calculate the product of elements of the array in the range L to R (both inclusive) and print the number of zeros
 *
 * at the end of the result.
 *
 *
 * 2)TYPE 2-> 0 L R V (where L,R,V are integers)
 *
 *
 * For this query you have to set the value of all the elements in the array ranging from L to R (both inclusive) to V
 *
 *
 *
 * ========
 * Input:
 * ========
 *
 * 5
 * [1 , 3 , 5 , 8 , 9]
 *
 * 3
 * 1 2 5
 * 0 1 4 10
 * 1 1 5
 *
 * =========
 * Output:
 * =========
 * 1
 * 4
 *
 */


/**
 *
 * SEGMENT TREE PROBLEM
 *
 * we store powers of 2 and 5 at each node (leaf and internal)
 *
 *
 */

class Reader {
    BufferedReader br;
    StringTokenizer st;

    public Reader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }


    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}


public class p3_cc_COUNZ {


    public static void main(String[] args) {

        Reader sc = new Reader();
        int n = sc.nextInt();
        int[] arr = new int[n];


        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Seg_tree_3 tree = new Seg_tree_3(arr);

        int q = sc.nextInt();
        while (q-- > 0) {
            int type = sc.nextInt();

            //print zeros
            if (type == 1) {
                int qs = sc.nextInt();
                int qe = sc.nextInt();
                int ans = tree.find_zeros(qs, qe);
                System.out.println(ans);
            }

            //update quey
            else {
                int qs = sc.nextInt();
                int qe = sc.nextInt();
                int new_val = sc.nextInt();
                tree.update(qs, qe, new_val);
            }
        }


//        int[] arr = {1, 3, 5, 8, 9};
//        Seg_tree_3 tree = new Seg_tree_3(arr);
//        System.out.println(tree.find_zeros(1, 4));
//        tree.update(1, 4, 10);
//        System.out.println(tree.find_zeros(1, 5));


    }
}


class Seg_tree_3 {

    int[][] TREE;
    int N;
    int MAX_QUERY_IDX;
    Map<Integer, int[]> hmap;

    Seg_tree_3(int[] arr) {
        int n = arr.length;
        this.N = 4 * n;
        MAX_QUERY_IDX = n;
        hmap = new HashMap<>();
//        int x = (int)Math.ceil(Math.log(n) / Math.log(2));
//        int max_size = 2 * (int)Math.pow(2, n) - 1;

        TREE = new int[2][N];
        form_tree(arr, 1, 0, n - 1);
    }

    void form_tree(int[] arr, int tree_idx, int rs, int re) {
        if (rs > re) {
            return;
        }
        if (rs == re) {
            int[] powers = get_powers(arr[rs]);
            TREE[0][tree_idx] = powers[0];
            TREE[1][tree_idx] = powers[1];
            return;
        }

        int mid = (rs + re) / 2;
        int left_tree_idx = 2 * tree_idx;
        int right_tree_idx = 2 * tree_idx + 1;

        form_tree(arr, left_tree_idx, rs, mid);
        form_tree(arr, right_tree_idx, mid + 1, re);

        TREE[0][tree_idx] = TREE[0][left_tree_idx] + TREE[0][right_tree_idx];
        TREE[1][tree_idx] = TREE[1][left_tree_idx] + TREE[1][right_tree_idx];

    }


    int find_zeros(int qs, int qe) {
        int[] x = find_zeros_util(qs - 1, qe - 1, 0, MAX_QUERY_IDX - 1, 1);
        return Math.min(x[0], x[1]);
    }

    private int[] find_zeros_util(int qs, int qe, int rs, int re, int tree_idx) {
        if (rs > re || re < qs || qe < rs) {
            return new int[]{0, 0};
        }

        if (rs >= qs && re <= qe) {
            return new int[]{TREE[0][tree_idx], TREE[1][tree_idx]};
        }

        int mid = (rs + re) / 2;
        int[] left = find_zeros_util(qs, qe, rs, mid, 2 * tree_idx);
        int[] right = find_zeros_util(qs, qe, mid + 1, re, 2 * tree_idx + 1);

        return new int[]{left[0] + right[0], left[1] + right[1]};
    }


    void update(int qs, int qe, int new_val) {
        update_util(qs - 1, qe - 1, 0, MAX_QUERY_IDX - 1, 1, new_val);
    }

    private void update_util(int qs, int qe, int rs, int re, int tree_idx, int new_val) {
        if (rs > re || re < qs || qe < rs) {
            return;
        }

        if (rs == re) {
            int[] powers = get_powers(new_val);
            TREE[0][tree_idx] = powers[0];
            TREE[1][tree_idx] = powers[1];
            return;
        }

//        int mid = (rs + re) / 2;
        int mid = rs + re >> 1;
        int left_tree_idx = 2 * tree_idx;
        int right_tree_idx = 2 * tree_idx + 1;
        update_util(qs, qe, rs, mid, 2 * tree_idx, new_val);
        update_util(qs, qe, mid + 1, re, 2 * tree_idx + 1, new_val);
        TREE[0][tree_idx] = TREE[0][left_tree_idx] + TREE[0][right_tree_idx];
        TREE[1][tree_idx] = TREE[1][left_tree_idx] + TREE[1][right_tree_idx];
    }


    private int[] get_powers(int x) {
        if (!hmap.containsKey(x)) {
            int[] pow = prime(x);
            hmap.put(x, new int[]{pow[0], pow[1]});
        }
        return hmap.get(x);
    }


    private int[] prime(int a) {
        int ret[] = new int[2];
        while (a > 0 && a % 2 == 0) {
            ret[0]++;
            a /= 2;
        }
        while (a > 0 && a % 5 == 0) {
            ret[1]++;
            a /= 5;
        }
        return ret;
    }

}
