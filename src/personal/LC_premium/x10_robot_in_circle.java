package personal.LC_premium;

/**
 * leetcode id : 1041
 *
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:
 *
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degrees to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 *
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 *
 */


/**
 *
 * the important thing to note here is , we have to simulate infinite loop of the intructio strng
 *
 *
 *
 *  we check whetehr the robot get back to the position (0,0)
 *  if yes, return true as the robot donot go out of the circle.
 *
 *  We check whether the direction is still North,
 *  then it will sure go out of the circle, so return false.
 *
 *
 *  If none of the above condition satisfies, then also the robot will be some where inside the circle, so return true.
 */

public class x10_robot_in_circle {

    public static void main(String[] args) {

        String input = "GL";
        boolean ans = new Solution_10().function(input);
        System.out.println(ans);

    }
}


class Solution_10 {

    boolean function(String input) {

        int x = 0;
        int y = 0;
        String direction = "north";

        for (char c : input.toCharArray()) {
            if (c == 'L') {
                direction = get_dir(c, direction);
            } else if (c == 'R') {
                direction = get_dir(c, direction);
            } else if (c == 'G') {
                if (direction.equals("north")) {
                    x++;
                } else if (direction.equals("east")) {
                    y++;
                } else if (direction.equals("south")) {
                    x--;
                } else if (direction.equals("west")) {
                    y--;
                }
            }
        }

        if (x == 0 && y == 0) {
            return true;
        } else {
            //direction should not be initial direction
            //here initially we face north
            return !direction.equals("north");
        }

    }


    String get_dir(char cmd, String curr) {
        String new_dir = "";
        if (cmd == 'L') {
            if (curr.equals("north")) {
                new_dir = "west";
            } else if (curr.equals("east")) {
                new_dir = "north";
            } else if (curr.equals("south")) {
                new_dir = "east";
            } else if (curr.equals("west")) {
                new_dir = "south";
            }
        } else if (cmd == 'R') {
            if (curr.equals("north")) {
                new_dir = "east";
            } else if (curr.equals("east")) {
                new_dir = "south";
            } else if (curr.equals("south")) {
                new_dir = "west";
            } else if (curr.equals("west")) {
                new_dir = "north";
            }
        }

        return new_dir;
    }

}
