package personal.CP.CP_qstion;

import personal.aaa_shared.Reader;

import java.util.*;

/**
 *
 * code : CTIME
 *
 * Chef's college is conducting an online exam, where his camera will be monitored by one or more invigilators (supervisors). Once again, Chef failed to prepare for the exam on time and decided to google answers during it.
 *
 * The exam starts at time 0 and ends at time F minutes.
 * Chef needs a total of K minutes of googling during the exam in order to pass it.
 * There are N invigilators (numbered 1 through N);
 * for each valid i, the i-th invigilator monitors Chef's camera during the time interval starting at Si minutes
 * since the start of the exam and ending at Ei minutes (where 0≤Si≤Ei≤F).
 *
 * He was resourceful enough to somehow get hold of these times and
 * now he wants to know if he would be able to pass the exam
 * if he googles the answers during the times when no one is looking at his camera.
 *
 *
 */

/**
 *
 *
 * similar to merge intervals problem
 *
 *
 *
 *
 *
 */

public class cc1_cheating_time {

    public static void main(String[] args) {
        Reader r = new Reader();
        int t = r.nextInt();
        Solution_4 s = new Solution_4();

        while (t-- > 0) {
            int n = r.nextInt();
            int k = r.nextInt();
            int f = r.nextInt();


            int[][] arr = new int[n][2];

            for (int i = 0; i < n; i++) {
                arr[i][0] = r.nextInt();
                arr[i][1] = r.nextInt();
            }

            String ans = s.fun(n, k, f, arr);
            System.out.println(ans);
        }
    }
}


class Solution_4 {

    String fun(int n, int k, int f, int[][] arr) {

        if (k == 0) {
            return "yes";
        }


        //sort the intervals in increasing order of start time
        //if start times are equal , sort according to ascending order of end time
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0])
                    return a[0] - b[0];
                return a[1] - b[1];
            }
        });

        List<int[]> merged_intervals = new LinkedList<>();

        merged_intervals.add(arr[0]);

        for (int i = 1; i < n; i++) {
            int last_idx = merged_intervals.size() - 1;
            int[] prev = merged_intervals.get(last_idx);
            int[] curr = arr[i];

            //prev and curr start at same time
            if (prev[0] == curr[0]) {
                merged_intervals.get(last_idx)[1] = Math.max(prev[1], curr[1]);
            }

            //curr-starts after prev-start , but there is overlap
            //prev-ends after curr-start
            else if (prev[1] <= curr[0]) {
                merged_intervals.get(last_idx)[1] = Math.max(prev[1], curr[1]);
            }

            //no overlap : push new entry in list
            else
                merged_intervals.add(arr[i]);
        }


        int cheat_time = 0;
        cheat_time += merged_intervals.get(0)[0];
        for (int i = 1; i < merged_intervals.size(); i++) {
            //(curr-start) - (prev-end)
            cheat_time += merged_intervals.get(i)[0] - merged_intervals.get(i - 1)[1];
        }

        cheat_time += f - merged_intervals.get(merged_intervals.size() - 1)[1];


        return (cheat_time >= k) ? "yes" : "no";


    }


}