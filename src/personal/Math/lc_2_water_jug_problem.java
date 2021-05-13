package personal.Math;

/**
 *
 *
 * leetcode-id : 365
 *
 * You are given two jugs with capacities jug1Capacity and jug2Capacity liters. There is an infinite amount of water supply available. Determine whether it is possible to measure exactly targetCapacity liters using these two jugs.
 *
 * If targetCapacity liters of water are measurable, you must have targetCapacity liters of water contained within one or both buckets by the end.
 *
 * Operations allowed:
 *
 * Fill any of the jugs with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full, or the first jug itself is empty
 *
 *
 */

/**
 *
 * using GCD concept
 *
 *
 */


public class lc_2_water_jug_problem {

    public static void main(String[] args) {

        System.out.println(new Solution_2().function(3, 5, 4));


    }
}

class Solution_2 {


    boolean function(int c1, int c2, int target) {
        if (target == 0 || c1 == target || c2 == target || c1 + c2 == target) {
            return true;
        } else if (c1 + c2 < target || target < 0) {
            return false;
        }

        if (c1 > c2) {
            return target % gcd(c1, c2) == 0;
        } else {
            return target % gcd(c2, c1) == 0;
        }
    }

    int gcd(int larger, int smaller) {
        if (smaller == 0) {
            return larger;
        }
        return gcd(smaller, larger % smaller);
    }
}
