package io.github.surakattula_rohith.dsa.binary_tree;

/**
 * Balanced Binary Tree
 *
 * A binary tree is height-balanced if:
 *      for every node, the height difference between
 *      left and right subtrees is at most 1.
 *
 * This file contains TWO approaches:
 *
 * ---------------------------------------------------
 * 1) Top-Down (Naive) Approach
 * ---------------------------------------------------
 *
 * Idea:
 * For every node:
 *  - compute the height of left subtree
 *  - compute the height of right subtree
 *  - check balance condition
 *  - recursively check left and right subtrees
 *
 * Problem:
 * Height of the same subtree is recomputed multiple times.
 *
 * Time Complexity:
 *      O(n^2) in the worst case (skewed tree)
 *
 * Space Complexity:
 *      O(h) recursion stack
 *
 *
 * ---------------------------------------------------
 * 2) Bottom-Up (Optimized) Approach
 * ---------------------------------------------------
 *
 * Key Observation:
 * Height and balance can be computed together in ONE DFS.
 *
 * Strategy:
 *  - Return height if subtree is balanced
 *  - Return -1 immediately if subtree is unbalanced
 *
 * This allows early termination and avoids recomputation.
 *
 * Time Complexity:
 *      O(n)  -> each node visited once
 *
 * Space Complexity:
 *      O(h) recursion stack
 *
 * Where:
 *      n = number of nodes
 *      h = height of the tree
 */
public class isBalancedBinaryTree {

    /* ---------------------------------------------------
     * Definition for a binary tree node.
     * --------------------------------------------------- */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /* ===================================================
     * 1) Top-Down Approach (Naive)
     * =================================================== */

    public boolean isBalancedTopDown(TreeNode root) {
        if (root == null) return true;

        int leftHeight = depth(root.left);
        int rightHeight = depth(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) return false;

        return isBalancedTopDown(root.left)
                && isBalancedTopDown(root.right);
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    /* ===================================================
     * 2) Bottom-Up Approach (Optimized)
     * =================================================== */

    public boolean isBalancedBottomUp(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;

        int left = height(node.left);
        if (left == -1) return -1;

        int right = height(node.right);
        if (right == -1) return -1;

        if (Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }
}
