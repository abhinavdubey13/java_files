package personal.CP;

/**
 *
 */

/**
 *
 * cache the solution using map
 *
 */


public class tt2 {

    public static void main(String[] args) {

        Reader sc = new Reader();
        int t = sc.nextInt();
        tt2_soln solution = new tt2_soln();
        while (t-- > 0) {
            //taking row and col inp
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            int ans = solution.function(x1, y1, x2, y2);
            System.out.println(ans);
        }


    }
}


class tt2_soln {


    int function(int x1, int y1, int x2, int y2) {
        int[][] matrix = new int[x2][y2];
        fill_matrix(matrix);
        int d1 = find_dist(x1, y1);
        int d2 = find_dist(x2, y2);
        return d2 - d1 + matrix[x1 - 1][y1 - 1];
    }


    void fill_matrix(int[][] arr) {
        int r = arr.length;
        int c = arr[0].length;
        arr[0][0] = 1;

        //first row
        int val_to_add = 1;
        for (int col = 1; col < c; col++) {
            arr[0][col] = arr[0][col - 1] + val_to_add;
            val_to_add++;
        }

        //first col
        val_to_add = 2;
        for (int row = 1; row < c; row++) {
            arr[row][0] = arr[row - 1][0] + val_to_add;
            val_to_add++;
        }


        for (int i = 1; i < r; i++) {
            val_to_add = i + 1;
            for (int j = 1; j < c; j++) {
                arr[i][j] = arr[i][j - 1] + val_to_add;
                val_to_add++;
            }
        }


    }

    int find_dist(int x, int y) {

        int xi = 1;
        int yi = 1;


        int val_to_add = 2;

        int path_sum = 1;

        int row = 1;

        while (xi + val_to_add <= x) {
            int new_x = xi + val_to_add;
            path_sum += new_x;
            xi = new_x;
            val_to_add++;
            row++;
        }


        row--;
        val_to_add = row;

        while (yi + val_to_add <= y) {
            int new_x = yi + val_to_add;
            path_sum += new_x;
            yi = new_x;
            val_to_add++;
        }

        return path_sum;


    }


}
