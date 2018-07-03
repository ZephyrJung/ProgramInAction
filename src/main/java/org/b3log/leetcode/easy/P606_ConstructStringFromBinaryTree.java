package org.b3log.leetcode.easy;

import org.b3log.leetcode.common.TreeNode;

/**
 * @author : yu.zhang
 * Date : 2018/7/3 上午8:46
 * Email : yu.zhang@7fresh.com
 **/
public class P606_ConstructStringFromBinaryTree {
    public static String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(String.valueOf(t.val));
        if (t.left == null && t.right == null) {
            return sb.toString();
        }
        if (t.left == null) {
            sb.append("()");
        } else {
            sb.append("(").append(tree2str(t.left)).append(")");
        }
        if (t.right != null) {
            sb.append("(").append(tree2str(t.right)).append(")");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TreeNode test1 = new TreeNode(1);
        test1.left = new TreeNode(2);
        test1.left.left = new TreeNode(4);
        test1.right = new TreeNode(3);
        System.out.println(tree2str(test1));

        TreeNode test2 = new TreeNode(1);
        test2.left = new TreeNode(2);
        test2.left.right = new TreeNode(4);
        test2.right = new TreeNode(3);
        System.out.println(tree2str(test2));
    }
}
