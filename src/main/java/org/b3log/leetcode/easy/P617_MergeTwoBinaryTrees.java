package org.b3log.leetcode.easy;

import org.b3log.leetcode.common.TreeNode;

import java.util.Stack;

/**
 * @author : yu.zhang
 * Date : 2018/6/14 上午9:08
 * Email : yu.zhang@7fresh.com
 **/
public class P617_MergeTwoBinaryTrees {

    // [16 ms]
    public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        Stack<TreeNode[]> stack = new Stack();
        stack.push(new TreeNode[]{t1, t2}); // any node pair in stack much be both non-null
        while (!stack.isEmpty()) {
            TreeNode[] t = stack.pop();
            t[0].val += t[1].val;
            // case 1: tree1 subtree null:                            put tree2 subtree to tree1, return
            // case 2: tree1 subtree not null, tree2 subtree null     do nothing, return
            // case 3: tree1 subtree not null, tree2 subtreenot null: put to stack
            /////// deal with left tree
            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else if (t[1].left != null) {
                stack.push(new TreeNode[]{t[0].left, t[1].left});
            }
            ////// deal with right tree
            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else if (t[1].right != null) {
                stack.push(new TreeNode[]{t[0].right, t[1].right});
            }
        }
        return t1;
    }

    // leetcode [18 ms]
    public static TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        TreeNode t = new TreeNode(0);
        recurseTree(t, t1, t2);
        return t;
    }

    // [16 ms]
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        TreeNode t = new TreeNode(0);
        recurseTree(t, t1, t2);
        return t;
    }

    private static void recurseTree(TreeNode t, TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return;
        }
        if (t1 == null) {
            t.val = t2.val;
            if (t2.left != null) {
                t.left = new TreeNode(0);
                recurseTree(t.left, null, t2.left);
            }
            if (t2.right != null) {
                t.right = new TreeNode(0);
                recurseTree(t.right, null, t2.right);
            }
        }
        if (t2 == null) {
            t.val = t1.val;
            if (t1.left != null) {
                t.left = new TreeNode(0);
                recurseTree(t.left, t1.left, null);
            }
            if (t1.right != null) {
                t.right = new TreeNode(0);
                recurseTree(t.right, t1.right, null);
            }
        }
        if (t1 != null && t2 != null) {
            t.val = t1.val + t2.val;
            if (t1.left != null || t2.left != null) {
                t.left = new TreeNode(0);
                recurseTree(t.left, t1.left, t2.left);
            }
            if (t1.right != null || t2.right != null) {
                t.right = new TreeNode(0);
                recurseTree(t.right, t1.right, t2.right);
            }
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(3);
        t1.left.left = new TreeNode(5);
        t1.right = new TreeNode(2);
        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(1);
        t2.left.right = new TreeNode(4);
        t2.right = new TreeNode(3);
        t2.right.right = new TreeNode(7);
        TreeNode t = mergeTrees(t1, t2);
        System.out.println(t == null);
    }
}
