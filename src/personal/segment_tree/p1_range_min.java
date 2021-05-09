package personal.segment_tree;


/**
 * TIME-COMPLEXITIES
 *
 * 1. building tree       : O(N)
 * 2. get min in range    : O(log N)
 * 3. single index update : O(log N)
 * 4. range update        : O(N)   depends on range , if range in all elements then O(N)
 *
 */


public class p1_range_min {

    public static void main(String[] args) {
//        int[] arr = {11, 12, 13, 14};
        int[] arr= {1, 3, 2, 7, 9, 11};

        Segment_tree_1 tree = new Segment_tree_1(arr);
        System.out.println(tree.get_min_for_query(1, 5));
        System.out.println(tree.get_min_for_query(0, 5));

        tree.update(0, -1);
        System.out.println(tree.get_min_for_query(1, 5));
        System.out.println(tree.get_min_for_query(0, 5));

        System.out.println();
    }


}


class Segment_tree_1 {

    int N;
    int MAX_QUERY_INDEX;
    int[] TREE;

    Segment_tree_1(int[] arr) {
        int n = arr.length;
        MAX_QUERY_INDEX = n; //for finding max/min etc : query index must be valid
        this.N = 4 * n + 1;
        TREE = new int[N];

        form_tree(arr, 0, n - 1, 1);
    }

    void form_tree(int[] arr, int range_start, int range_end, int idx_to_fill) {

        //base case
        if (range_start > range_end) {
            return;
        }

        //leaf nodes : these are actual values in arr[]
        else if (range_start == range_end) {
            TREE[idx_to_fill] = arr[range_start];
        }

        //internal nodes
        else {
            int mid = (range_start + range_end) / 2;
            int left_child_idx = 2 * idx_to_fill;
            int right_child_idx = 2 * idx_to_fill + 1;

            form_tree(arr, range_start, mid, left_child_idx);//left
            form_tree(arr, mid + 1, range_end, right_child_idx);//right

            //fill current index : POST order traversal
            int min_val = Math.min(TREE[left_child_idx], TREE[right_child_idx]);
            this.TREE[idx_to_fill] = min_val;
        }
    }

    int get_min_for_query(int q_start, int q_end) {

        //validating query indices first
        if (q_start >= 0 && q_start < MAX_QUERY_INDEX && q_end >= 0 && q_end < MAX_QUERY_INDEX && q_start <= q_end) {
            return get_min_util(q_start, q_end, 0, MAX_QUERY_INDEX - 1, 1);
        } else {
            System.out.println("invalid query index");
            return Integer.MAX_VALUE;
        }
    }

    private int get_min_util(int q_start, int q_end, int r_start, int r_end, int tree_idx) {

        //case-1 : no overlap : return INFINITY
        if (r_end < q_start || r_start > q_end) {
            return Integer.MAX_VALUE;
        }

        //case-2 : complete overlap
        else if (q_start <= r_start && r_end <= q_end) {
            return this.TREE[tree_idx];
        }

        //case-3 : partial overlap
        else {
            int mid = (r_start + r_end) / 2;
            int left_idx = 2 * tree_idx;
            int right_idx = 2 * tree_idx + 1;

            int left_min_val = get_min_util(q_start, q_end, r_start, mid, left_idx);
            int right_min_val = get_min_util(q_start, q_end, mid + 1, r_end, right_idx);

            //NOTE : TREE[idx] is NOT to be used here
            int final_min = Math.min(left_min_val, right_min_val);
            return final_min;
        }

    }


    void update(int index_to_be_updated, int new_val) {
        if (index_to_be_updated >= MAX_QUERY_INDEX || index_to_be_updated < 0) {
            System.out.println("invalid index");
            return;
        }
        update_util(index_to_be_updated, new_val, 1, 0, MAX_QUERY_INDEX - 1);
    }

    private void update_util(int index_to_be_updated, int new_val, int tree_idx, int r_start, int r_end) {

        //no overlap : idx_to_update is outside [r_start,r_end]
        if (r_start > r_end || index_to_be_updated < r_start || index_to_be_updated > r_end) {
            return;
        }


        //leaf node
        else if (r_start == r_end) {
            this.TREE[tree_idx] = new_val;
            return;
        }

        //partial or complete overlap
        int mid = (r_start + r_end) / 2; //this is for range
        int left_child_idx = 2 * tree_idx;
        int right_child_idx = 2 * tree_idx + 1;
        update_util(index_to_be_updated, new_val, left_child_idx, r_start, mid);
        update_util(index_to_be_updated, new_val, right_child_idx, mid + 1, r_end);

        this.TREE[tree_idx] = Math.min(TREE[left_child_idx], TREE[right_child_idx]);
    }


    void range_update(int q_start, int q_end, int new_val) {
        if (q_start < 0 || q_start >= MAX_QUERY_INDEX || q_end < 0 || q_end >= MAX_QUERY_INDEX || q_start > q_end) {
            System.out.println("invalid query range");
            return;
        }

        //if range is same number , it means we are updating only 1 value
        if (q_start == q_end) {
            update(q_start, new_val);
        }

        range_update_util(q_start, q_end, new_val, 1, 0, MAX_QUERY_INDEX - 1);
    }


    private void range_update_util(int q_start, int q_end, int new_val, int tree_idx, int r_start, int r_end) {

        //case-1 : no overlap
        if (r_start > r_end || r_end < q_start || r_start > q_end) {
            return;
        }

        //leaf node
        if (r_start == r_end) {
            this.TREE[tree_idx] = new_val;
            return;
        }

        //case-2  : complete overlap
        //case-3 : partial overlap
        int mid = (r_start + r_end) / 2;
        int left_child_idx = 2 * tree_idx;
        int right_child_idx = 2 * tree_idx + 1;
        range_update_util(q_start, q_end, new_val, 2 * tree_idx, r_start, mid);
        range_update_util(q_start, q_end, new_val, 2 * tree_idx + 1, mid + 1, r_end);

        this.TREE[tree_idx] = Math.min(TREE[left_child_idx], TREE[right_child_idx]);
    }


}
