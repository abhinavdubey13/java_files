package personal.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode id : 1376
 *
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one with headID.
 *
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination relationships have a personal.tree structure.
 *
 * The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.
 *
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).
 *
 * Return the number of minutes needed to inform all the employees about the urgent news.
 *
 *
 * =========
 * example :
 * =========
 */

/**
 * =============
 * APPROACH :
 * =============
 * using BFS
 *
 * first form graph , then apply BFS
 *
 * we will not need visited[] here
 *
 *
 * */

class x39_inform_all_employees {

    public static void main(String[] args) {
        int n = 6;
        int head = 2;
        int[] manager = {2, 2, -1, 2, 2, 2};
        int[] time = {0, 0, 1, 0, 0, 0};

        int answer = new Solution39().function(n, head, manager, time);
        System.out.println(answer);
    }

}


class Solution39 {

    int function(int n, int head, int[] manager, int[] time) {


        //form adjacency list : list of sub-ordinates a person in managing
        List<List<Integer>> graph = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int i = 0; i < n; i++) {
            //ith employee is being managed by manager[i]
            if (manager[i] >= 0) {
                graph.get(manager[i]).add(i);
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{head, 0});

        int max_time = 0;
        while (q.size() > 0) {
            int[] polled = q.poll();
            int manager_id = polled[0];
            int time_till_now = polled[1];
            max_time = Math.max(max_time, time_till_now);

            for (Integer sub : graph.get(manager_id)) {
                q.add(new int[]{sub, time_till_now + time[manager_id]});
            }
        }

        return max_time;


    }


}
