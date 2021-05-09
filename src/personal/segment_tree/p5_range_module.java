package personal.segment_tree;

import java.util.*;


public class p5_range_module {


}


class RangeModule {

    int N = 1000000001;
    int MAX_VALID_QUERY = N;
    boolean[] TREE;

    public RangeModule() {
        TREE = new boolean[N];
    }

    public void addRange(int left, int right) {
        add_util(left - 1, right - 2, 0, MAX_VALID_QUERY - 1, 1);
    }

    public boolean queryRange(int left, int right) {
        return query_util(left - 1, right - 2, 0, MAX_VALID_QUERY - 1, 1);

    }

    public void removeRange(int left, int right) {
        remove_util(left - 1, right - 2, 0, MAX_VALID_QUERY - 1, 1);
    }


    private void add_util(int qs, int qe, int rs, int re, int tree_idx) {
        if (rs > re || qs > re || qe < rs) {
            return;
        }

        if (rs == re) {
            TREE[tree_idx] = true;
            return;
        }

        int mid = rs + re >> 2;
        int left_child_idx = 2 * tree_idx;
        int right_child_idx = 2 * tree_idx + 1;
        add_util(qs, qe, rs, mid, left_child_idx);
        add_util(qs, qe, mid + 1, re, right_child_idx);
        TREE[tree_idx] = TREE[left_child_idx] && TREE[right_child_idx];
    }


    private boolean query_util(int qs, int qe, int rs, int re, int tree_idx) {

        //no overlap
        if (rs > re || qs > re || qe < rs) {
            return true;
        }


        //complete overlap
        if (rs == re || rs >= qs && qe >= re) {
            return TREE[tree_idx];
        }

        //partial overlap
        int mid = rs + re >> 2;
        int left_child_idx = 2 * tree_idx;
        int right_child_idx = 2 * tree_idx + 1;
        boolean a = query_util(qs, qe, rs, mid, left_child_idx);
        boolean b = query_util(qs, qe, mid + 1, re, right_child_idx);
        return a && b;
    }

    private void remove_util(int qs, int qe, int rs, int re, int tree_idx) {
        if (rs > re || qs > re || qe < rs) {
            return;
        }

        if (rs == re) {
            TREE[tree_idx] = false;
            return;
        }

        int mid = rs + re >> 2;
        int left_child_idx = 2 * tree_idx;
        int right_child_idx = 2 * tree_idx + 1;
        remove_util(qs, qe, rs, mid, left_child_idx);
        remove_util(qs, qe, mid + 1, re, right_child_idx);
        TREE[tree_idx] = TREE[left_child_idx] && TREE[right_child_idx];
    }


}
