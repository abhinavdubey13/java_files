package personal.LC_premium;

import java.util.*;

/**
 * leetcode id : 759
 *
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
 * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 *
 *
 * example :
 *
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite
 */


/**
 * using priority queue
 *
 *
 * Overall, we don't need to care which interval belongs to which one.
 * Just put them all in one PQ,
 * and merge the overlapped ones.
 *
 * Finally get the free period for everybody.
 */

class Interval {
    public int start;
    public int end;

    public Interval() {
    }

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
}

public class x13_employee_free_time {

    public static void main(String[] args) {

        //int ans = new Solution_13().function();

    }
}


class Solution_13 {

    List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        PriorityQueue<Interval> q = new PriorityQueue<>((a, b) -> (a.start != b.start) ? a.start - b.start : a.end - b.end);

        for (List<Interval> list : schedule) {
            for (Interval i : list) {
                q.offer(i);
            }
        }


        List<Interval> answer = new LinkedList<>();

        if (q.size() == 0) {
            return answer;
        }


        Interval prev = q.poll();
        while (q.size() > 0) {
            Interval curr = q.poll();

            //overlapping
            if (curr.start <= prev.end) {
                curr.start = Math.min(curr.start, prev.start);
                curr.end = Math.max(curr.end, prev.end);
            } else {
                answer.add(new Interval(prev.end, curr.start));
            }

            prev = curr;
        }

        return answer;

    }

}
