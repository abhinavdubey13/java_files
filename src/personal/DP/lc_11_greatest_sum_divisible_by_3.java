package personal.DP;
import java.util.*;

/**
 * leetcode : 63
 *
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and space is marked as 1 and 0 respectively in the grid
 *
 *
 *
 */

/**
 *
 * convert obstacle cells to -1 and the solve
 *
 * ============
 * TC = r*c
 * SC = 1
 * ==========
 */


public class lc_11_greatest_sum_divisible_by_3 {

    public static void main(String[] args) {

        int[] arr = {3,6,5,1,8};

        int ans = new Solution11().function(arr);
        System.out.println(ans);
    }
}


class Solution11 {
    public int function(int[] nums) {

        Map<Integer,List<Integer>>hmap = new HashMap<>();


        for(int i : nums){
            int mod = i%3;
            if(!hmap.containsKey(mod)){
                hmap.put(mod,new LinkedList<>());
            }
            hmap.get(mod).add(i);
        }

        int ans=0;

        for(Integer i : hmap.get(0)){
            ans += i;
        }

//        if(hmap.)

//        List<Integer>min_list = (hmap.get(1).size() < hmap.get(2).size());

        return ans;


    }
}