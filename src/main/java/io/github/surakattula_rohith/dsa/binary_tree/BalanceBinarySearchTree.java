package io.github.surakattula_rohith.dsa.binary_tree;

import java.util.*;

/**
 * Balance a Binary Search Tree
 *
 * Given the root of a Binary Search Tree (BST),
 * convert it into a height-balanced BST
 * while keeping the same node values.
 *
 * A BST is height-balanced if:
 *      for every node, the height difference between
 *      left and right subtrees is at most 1.
 *
 * ---------------------------------------------------
 * Approach: Inorder Traversal + Divide and Conquer
 * ---------------------------------------------------
 *
 * Key Observations:
 * 1) Inorder traversal of a BST produces values in sorted order.
 * 2) A balanced BST can be built by always choosing
 *    the middle element as the root.
 *
 * Strategy:
 *  - Perform inorder traversal and store values in a list.
 *  - Recursively build a balanced BST from the sorted list.
 *
 * This guarantees balance because left and right subtrees
 * will have nearly equal number of nodes.
 *
 * ---------------------------------------------------
 * Time Complexity:
 *      O(n)
 *      - inorder traversal visits each node once
 *      - building the balanced tree visits each value once
 *
 * Space Complexity:
 *      O(n)
 *      - inorder list stores all node values
 *      - recursion stack up to tree height
 *
 * Where:
 *      n = number of nodes
 */
public class BalanceBinarySearchTree {

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
     * Main Method
     * =================================================== */

    public TreeNode balanceBST(TreeNode root) {

        // List to store BST values in sorted order
        List<Integer> inorder = new ArrayList<>();

        // Step 1: Convert BST to sorted list
        inorderTraversal(root, inorder);

        // Step 2: Build balanced BST from sorted list
        return buildBalancedBST(inorder, 0, inorder.size() - 1);
    }

    /* ===================================================
     * Step 1: Inorder Traversal
     * =================================================== */

    /**
     * Inorder traversal of BST.
     *
     * Traversal Order:
     *      Left -> Root -> Right
     *
     * For a BST, this produces values in sorted order.
     */
    private void inorderTraversal(TreeNode node, List<Integer> inorder) {
        if (node == null) return;

        inorderTraversal(node.left, inorder);
        inorder.add(node.val);
        inorderTraversal(node.right, inorder);
    }

    /* ===================================================
     * Step 2: Build Balanced BST
     * =================================================== */

    /**
     * Builds a height-balanced BST from a sorted list.
     *
     * Approach:
     *  - Choose the middle element as root
     *  - Recursively build left subtree from left half
     *  - Recursively build right subtree from right half
     *
     * This ensures height difference is at most 1.
     */
    private TreeNode buildBalancedBST(List<Integer> inorder, int left, int right) {

        // Base case: no elements to construct subtree
        if (left > right) return null;

        // Middle element becomes root
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(inorder.get(mid));

        // Recursively construct left and right subtrees
        root.left = buildBalancedBST(inorder, left, mid - 1);
        root.right = buildBalancedBST(inorder, mid + 1, right);

        return root;
    }
}
