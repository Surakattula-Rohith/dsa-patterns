package io.github.surakattula_rohith.dsa.binary_tree;

public class SumOfRootToLeafBinaryNumbers {
    /**
     * LeetCode 1022
     * <p>
     * Sum of Root To Leaf Binary Numbers
     * <p>
     * Approach:
     * - Use DFS traversal.
     * - Maintain a running integer representing the binary number formed so far.
     * - At each node:
     * current = current * 2 + node.val
     * (equivalent to left shift and append current bit)
     * <p>
     * - When a leaf node is reached,
     * return the computed binary value.
     * <p>
     * - Final answer = sum of all root-to-leaf binary values.
     * <p>
     * Time Complexity: O(N)
     * - Each node is visited once.
     * <p>
     * Space Complexity: O(H)
     * - Recursion stack height (H = height of tree).
     * - Worst case: O(N), Balanced tree: O(log N)
     */
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int current) {
        if (node == null)
            return 0;

        current = current * 2 + node.val;

        if (node.left == null && node.right == null)
            return current;

        return dfs(node.left, current) + dfs(node.right, current);
    }
}
