package personal.bit_manipulation;

import java.util.*;


/**
 *
 * leetcode : 1125
 *
 * In a project, you have a list of required skills req_skills, and a list of people.
 * The ith person people[i] contains a list of skills that the person has.
 *
 * Consider a sufficient team: a set of people such that for every required skill in req_skills,
 * there is at least one person in the team who has that skill.
 * We can represent these teams by the index of each person.
 *
 * For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
 * Return any sufficient team of the smallest possible size, represented by the index of each person.
 * You may return the answer in any order.
 *
 * It is guaranteed an answer exist
 *
 */


/**
 *
 * backtracking + bit manipulation
 *
 * https://www.youtube.com/watch?v=5gXNMGiqQbU&list=PL-Jc9J83PIiFJRioti3ZV7QabwoJK6eKe&index=6
 *
 *
 * bitwise OR
 *
 */

public class lc_1_minimum_developers {
    public static void main(String[] args) {


        String[] required_skills = {"algorithms", "math", "java", "reactjs", "csharp", "aws"};

        List<List<String>> people = new LinkedList<>();

        people.add(Arrays.asList("algorithms", "math", "java"));
        people.add(Arrays.asList("algorithms", "math", "reactjs"));
        people.add(Arrays.asList("java", "csharp", "aws"));
        people.add(Arrays.asList("reactjs", "csharp"));
        people.add(Arrays.asList("csharp", "math"));
        people.add(Arrays.asList("aws", "java"));

        int[] ans = new Solution_4().smallestSufficientTeam(required_skills, people);

        if (ans != null)
            for (int i : ans)
                System.out.print(i + " ");

        System.out.println();
    }
}


class Solution_4 {

    int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {

        Map<String, Integer> skill_idx_map = new HashMap<>();

        int i = 0;
        for (String s : req_skills) {
            skill_idx_map.put(s, i++);
        }

        int[][] bit_map = new int[people.size()][req_skills.length];

        for (i = 0; i < people.size(); i++) {
            List<String> person = people.get(i);
            for (String skill : person) {
                int skill_idx = skill_idx_map.get(skill);
                bit_map[i][skill_idx] = 1;
            }
        }

        int[] person_num = new int[people.size()];
        boolean[] person_selected = new boolean[people.size()];

        for (i = 0; i < bit_map.length; i++) {
            int num = get_decimal(bit_map[i]);
            person_num[i] = num;
        }


        Set<String> possible_ans = new HashSet<>();
        dfs(0, 0, req_skills.length, person_num, person_selected, possible_ans);



        //sort all answers in set , according to length and return the shortest
        return null;

    }

    void dfs(int idx, int skill_now, int req_skills, int[] person_num, boolean[] person_selected, Set<String> possible_ans) {

        if (idx >= person_num.length) {
            return;
        }

        if (check_set_bits(skill_now) == req_skills) {

            String ans = "";

            for (int i = 0; i < person_selected.length; i++) {
                if (person_selected[i]) {
                    ans += String.valueOf(i);
                }
            }

            possible_ans.add(ans);
        }

        int incl = skill_now | person_num[idx];
        int excl = skill_now;

        dfs(idx + 1, excl, req_skills, person_num, person_selected, possible_ans);

        person_selected[idx] = true;
        dfs(idx + 1, incl, req_skills, person_num, person_selected, possible_ans);
        person_selected[idx] = false;


    }

    int check_set_bits(int x) {
        int bits = 0;
        while (x > 0) {
            int sub = x & -x;
            x -= sub;
            bits++;
        }

        return bits;
    }


    int get_decimal(int[] arr) {
        int multiplier = 1;
        int decimal = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            decimal += multiplier * arr[i];
            multiplier *= 2;

        }
        return decimal;
    }


}