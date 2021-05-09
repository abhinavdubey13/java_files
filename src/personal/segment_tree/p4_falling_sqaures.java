package personal.segment_tree;

import java.util.*;

/**
 * leetcode id  : 699
 *
 *
 * There are several squares being dropped onto the X-axis of a 2D plane.
 *
 * You are given a 2D integer array positions where positions[i] = [lefti, sideLengthi]
 * represents the ith square with a side length of sideLengthi that is dropped with its left edge aligned with X-coordinate lefti.
 *
 * Each square is dropped one at a time from a height above any landed squares. It then falls downward (negative Y direction) until it either lands on the top side of another square or on the X-axis. A square brushing the left/right side of another square does not count as landing on it. Once it lands, it freezes in place and cannot be moved.
 *
 * After each square is dropped, you must record the height of the current tallest stack of squares.
 *
 * Return an integer array ans where ans[i] represents the height described above after dropping the ith square.
 *
 *
 *
 */

/**
 *
 * 1. compress the actual cordinated to 0,1,2 ...
 * 2. now create a segment tree , sized number of cordinated obtained in step 1
 * 3. for each block
 *      3.1 query tree for current max at the range of block , and add height to it
 *      3.2 update the tree with that added height
 *      3.3 add max to list
 *
 *
 */

public class p4_falling_sqaures {

    public static void main(String[] args) {

    }
}


class Solution {


    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> ans = new LinkedList<>();
        Map<Integer, Integer> cc = compressed_cordinate_map(positions);
        Seg_tree_4 tree = new Seg_tree_4(cc.size());
        int best_till_now = 0;

        for (int[] p : positions) {
            int L = cc.get(p[0]);
            int R = cc.get(p[0] + p[1] - 1);
            int H = tree.query(L, R) + p[1];
            tree.update(L, R, H);
            best_till_now = Math.max(best_till_now, H);
            ans.add(best_till_now);
        }

        return ans;

    }


    //if positions = [[100,100],[150,100]]
    //then the useful cordinates are :  100,199,150,249 bcz they only have some block over them
    //so we convert these into : 0,1,2,3 respectively
    private Map<Integer, Integer> compressed_cordinate_map(int[][] pos) {
        TreeSet<Integer> hset = new TreeSet<>();
        for (int[] p : pos) {
            hset.add(p[0]);
            hset.add(p[0] + p[1] - 1);
        }
        Map<Integer, Integer> cords = new HashMap<>();
        int mapped_val = 0;
        for (Integer i : hset) {
            cords.put(i, mapped_val);
            mapped_val++;
        }

        return cords;

    }
}


class Seg_tree_4 {
    int[] tree;
    int N;

    Seg_tree_4(int N) {
        this.N = N;
        int n = (1 << ((int) Math.ceil(Math.log(N) / Math.log(2)) + 1));
        tree = new int[n];
    }

    public int query(int L, int R) {
        return queryUtil(1, 0, N - 1, L, R);
    }

    //L,R : query_start and query_end
    private int queryUtil(int index, int s, int e, int L, int R) {
        // out of range
        if (s > e || s > R || e < L) {
            return 0;
        }
        // [L, R] cover [s, e]
        if (s >= L && e <= R) {
            return tree[index];
        }
        // Overlapped
        int mid = s + (e - s) / 2;
        return Math.max(queryUtil(2 * index, s, mid, L, R), queryUtil(2 * index + 1, mid + 1, e, L, R));
    }

    public void update(int L, int R, int h) {
        updateUtil(1, 0, N - 1, L, R, h);
    }

    private void updateUtil(int index, int s, int e, int L, int R, int h) {
        // out of range
        if (s > e || s > R || e < L) {
            return;
        }

        //this is important : we add a heign to every range , except only the no-overlap case
        //this is bcz block add up in height when dropped
        tree[index] = Math.max(tree[index], h);
        if (s != e) {
            int mid = s + (e - s) / 2;
            updateUtil(2 * index, s, mid, L, R, h);
            updateUtil(2 * index + 1, mid + 1, e, L, R, h);
        }
    }
}
