package org.b3log.leetcode;

import org.b3log.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/6/14 上午9:08
 * Email : yu.zhang@7fresh.com
 **/
public class P617_MergeTwoBinaryTrees {
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode t = t1;
        List<Integer> arrays = new ArrayList<>();
        while (t1 != null || t2 != null) {
            if (t1 != null && t2 != null) {
                t1.val = t1.val + t2.val;
            }
            if (t2 != null) {
                t1 = new TreeNode(t2.val);
            }
            t1 = t1.left;
            t2 = t2.left;
        }
        while (t1 != null) {
            if (t2 != null) {
                t1.val = t1.val + t2.val;
            }
            t1 = t1.left;
            t2 = t2.left;
        }
    }

    public Integer recurseTree(List<Integer> array, TreeNode t) {
        if (t == null) {
            return null;
        }
        array.add(t.val);
        if (t.left != null) {
            recurseTree(array, t.left);
        }
    }
}
