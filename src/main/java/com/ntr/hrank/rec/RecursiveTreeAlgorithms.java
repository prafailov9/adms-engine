package com.ntr.hrank.rec;

import com.ntr.hrank.TreeNode;

public class RecursiveTreeAlgorithms {


  /**
   * maxDepth(root(1))
   * Tree:
   * |root = 1: children: 2, 3
   * |node 2 -> 4, null
   * |node 3 -> null, null
   * |node 4 -> 5, null
   * |node 5 -> null, null
   * <p>
   * Stack Trace:
   * call 1:root = 1
   * -> base case: false
   * -> computes 1 + resolution of call 2(left)
   * call 2: root = 2
   * -> base case: false
   * -> computes 1 + resolution of call 3(left)
   * call 3: root = 4
   * -> base case: false
   * -> computes 1 + resolution of call 4(left)
   * call 4: root = 5
   * -> base case: false
   * -> computes 1 + resolution of call 5(left)
   * call 5: root = NULL
   * -> base case: true -> returns 0
   * call 4 resumes(root = 5):
   * -> writes 1 + 0 to maxLeft counter
   * -> computes 1 + resolution of call 6(right)
   * call 6: root = NULL
   * -> base case: true -> returns 0
   * call 4 resumes(root = 5):
   * -> writes 1 + 0 to maxRight counter
   * -> returns larger counter(1, 1) -> 1
   * call 3 resumes(root = 4):
   * -> writes 1 + 1 to maxLeft counter = 2
   * -> computes 1 + resolution of call 7(right)
   * call 7: root = NULL
   * -> base case: true -> returns 0
   * call 3 resumes(root = 4):
   * -> writes 1 + 0 to maxRight counter
   * -> returns larger counter(2, 1) -> 2
   * call 2 resumes(root = 2):
   * -> writes 1 + 2 to the maxLeft counter = 3
   * -> computes 1 + resolution of call 8(right)
   * call 8: root = NULL
   * -> base case: true -> returns 0
   * call 2 resumes(root = 2):
   * -> writes 1 + 0 to maxRight counter
   * -> returns larger counter(3, 1) -> 3
   * call 1 resumes(root = 1):
   * -> writes 1 + 3 to maxLeft counter
   * -> computes 1 + resolution of call 9(right)
   * call 9: root = NULL
   * -> base case: true -> returns 0
   * call 1 resumes(root = 1):
   * -> writes 1 + 0 to maxRight counter
   * -> returns larger counter(4, 1) -> 4
   */
  static int maxDepth(TreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }
    int leftCount = 1 + maxDepth(root.left);
    int rightCount = 1 + maxDepth(root.right);
    return Math.max(leftCount, rightCount);
  }

  /**
   * * Tree:
   * * |root = 1: children: 2, 3
   * * |node 2 -> 4, null
   * * |node 3 -> null, null
   * * |node 4 -> 5, null
   * * |node 5 -> null, null
   * Compare the current node's value with its children(left, right)
   * fm(null): return min_val
   * fm(5): compare root = 5, fm(l = MIN_VAL) and fm(right = MIN_VAL) -> return 5;
   * fm(4): compare root = 4, fm(l = 5) and fm(r = MIN_VAL) -> return 5;
   * fm(2): compare root = 2, fm(l = 5) and fm(r = MIN_VAL) -> return 5;
   * fm(3): compare root = 3, fm(l = MIN_VAL) and fm(r = MIN_VAL) -> returns 3;
   * fm(1): compare root = 1, fm(l = 5) and fm(r = 3) -> return 5;
   */
  static int findMax(TreeNode<Integer> root) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
  }

  /**
   * * Tree:
   * * |root = 1: children: 2, 3
   * * |node 2 -> 4, null
   * * |node 3 -> null, null
   * * |node 4 -> 5, null
   * * |node 5 -> null, null
   * Count the current node (1) plus its left and right subtrees
   * countNodes(null): return 0
   * countNodes(5): add 1 to countNodes(l = 0) and countNodes(right = 0) -> return 1;
   * countNodes(4): add 1 to countNodes(l = 1) and countNodes(r = 0) -> return 2;
   * countNodes(2): add 1 to countNodes(l = 2) and countNodes(r = 0) -> return 3;
   * countNodes(3): add 1 to countNodes(l = 0) and countNodes(r = 0) -> returns 1;
   * countNodes(1): add 1 to countNodes(l = 3) and countNodes(r = 1) -> return 5;
   */
  static int countNodes(TreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }
    return 1 + countNodes(root.left) + countNodes(root.right);
  }

  /**
   * /**
   * * * Tree:
   * * * |root = 1 v = 1: children: 2, 3
   * * * |node 2 v = 2 -> 4, null
   * * * |node 3 v = 3-> null, null
   * * * |node 4 v = 3 -> 5, null
   * * * |node 5 v = 5 -> null, null
   * * Add the current node's value with its children(left, right)
   * * treeSum(null): return 0
   * * treeSum(5): add 5 + treeSum(l = 0) + treeSum(r = 0) -> 5;
   * * treeSum(4): add 3 + treeSum(l = 5) + treeSum(r = 0) -> 8;
   * * treeSum(2): add 2 + treeSum(l = 8) + treeSum(r = 0) -> 10;
   * * treeSum(3): add 3 + treeSum(l = 0) + treeSum(r = 0) -> 3;
   * * treeSum(1): add 1 + treeSum(l = 10) + treeSum(r = 3) -> 15;
   */

  static int treeSum(TreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }
    return root.val + treeSum(root.left) + treeSum(root.right);
  }

  /**
   * Tree:
   * |root = 1 v = 1: children: 2, 3
   * |node 2 v = 2 -> 4, null
   * |node 3 v = 3-> null, null
   * |node 4 v = 4 -> 5, null
   * |node 5 v = 5 -> null, null
   * <p>
   * x = 5
   * <p>
   * At each step, compare and return 1 if the current node or its subtrees equal x
   * <p>
   * * contains(null, 5) -> 0;
   * * contains(5, 5): cmp current(5) to 5 or eval contains(null, 5) = 1 or contains(null, 5) =
   * 0 -> 1;
   * * contains(4, 5): cmp current(4) to 5 or eval contains(5, 5) = 1 or contains(null, 5) =
   * 0 -> 1;
   * * contains(2, 5): cmp current(2) to 5 or eval contains(4, 5) = 1 or contains(null, 5) =
   * 0 -> 1;
   * contains(3, 5): cmp current(3) to 5 or contains(null, 5) or contains(null, 5) → 0
   * contains(1, 5): 0 or contains(2, 5) = 1 or contains(3, 5) = 0 → 1
   */

  static boolean contains(TreeNode<Integer> root, int x) {
    if (root == null) {
      return false;
    }

    return root.val == x || contains(root.left, x) || contains(root.right, x);
  }

  static int countLeaves(TreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }
    int l = countLeaves(root.left);
    int r = countLeaves(root.right);

    return l == 0 && r == 0 ? 1 : l + r;
  }

  static boolean allEven(TreeNode<Integer> root) {
    if (root == null) {
      return true;
    }
    if (root.val % 2 != 0) {
      return false;
    }
    return allEven(root.left) && allEven(root.right);
  }

  static int heightDifference(TreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }

    int left = maxDepth(root.left);
    int right = maxDepth(root.right);

    return Math.abs(left - right);
  }

  /**
   * Return the number of internal nodes in the tree.
   * (An internal node has at least one child.)
   * root = null -> 0
   * single node -> 0
   * chain of 3 nodes -> 2
   * Tree:
   * |root: children -> n5, n2
   * |n5: leaf
   * |n2: children n4, n3
   * |n4: leaf
   * |n3: leaf
   */
  static int countInternalNodes(TreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }
    // track current count in left and right subtrees;
    int l = countInternalNodes(root.left);
    int r = countInternalNodes(root.right);

    // if at least 1 child exists, add 1 to current count;
    if (isInternal(root)) {
      return 1 + l + r;
      // else -> this is not an internal node -> return 0;
    }
    return 0;
  }

  /**
   * Return the sum of all leaf node values.
   * sumLeaves(5) -> 5
   * sumLeaves(1) -> writes 5 to sumLeft, recurses the right subtree
   * sumLeaves(4) -> 4
   * sumLeaves(2) -> writes 4 to sumLeft, recurses the right subtree
   * sumLeaves(3) -> 3
   * sumLeaves(2) -> writes 3 to sumRight -> returns 4 + 3 = 7
   * sumLeaves(2) -> writes 7 to sumRight -> returns 5 + 7 = 12
   */
  static int sumLeaves(TreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }
    int sumLeft = sumLeaves(root.left);
    int sumRight = sumLeaves(root.right);

    // leaves return here
    if (isLeaf(root)) {
      return root.val;
    }
    // internals return here
    return sumLeft + sumRight;
  }

  /**
   * every node has at most one child
   */
  static boolean isChain(TreeNode<Integer> root) {
    if (root == null) {
      return true;
    }

    // if both children exist -> not a chain
    if (isFull(root)) {
      return false;
    } else if (isLeaf(root)) {
      return true;
    }

    return isChain(root.left) || isChain(root.right);
  }

  /**
   * count nodes with exactly one child
   */
  static int countSingleChildNodes(TreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }

    int count = countSingleChildNodes(root.left) + countSingleChildNodes(root.right);
    if ((root.left != null ^ root.right != null)) {
      return 1 + count;
    }
    // if no children OR both children -> false
    return count;
  }

  /**
   * count only nodes with 2 children
   */
  static int countFullNodes(TreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }

    int count = countFullNodes(root.left) + countFullNodes(root.right);
    if (isFull(root)) {
      return 1 + count;
    }
    // if no children OR both children -> false
    return count;
  }

  static int findMin(TreeNode<Integer> root) {
    if (root == null) {
      return Integer.MAX_VALUE;
    }

    return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
  }

  static int sumInternalNodes(TreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }
    // sum values of internal nodes
    int sumLeft = sumInternalNodes(root.left);
    int sumRight = sumInternalNodes(root.right);

    if (isLeaf(root)) {
      return 0;
    }
    return root.val + sumLeft + sumRight;
  }

  static boolean allPositive(TreeNode<Integer> root) {
    if (root == null) {
      return true;
    }
    if (root.val < 0) {
      return false;
    }
    return allPositive(root.left) && allPositive(root.right);
  }

  // Class to track best depth + sum pairs
  private static class DepthSum {

    int bestDepth;
    int bestSum;
  }

  static int longestPathSum(TreeNode<Integer> root) {
    var ds = new DepthSum();
    dfs(root, ds, 0, 0);
    return ds.bestSum;
  }

  private static void dfs(TreeNode<Integer> node, DepthSum depthSum, int depth, int sum) {
    if (node == null) {
      return;
    }
    // increment state
    depth++;
    sum += node.val;
    // at any leaf:
    // write current depth and sum if the current best depth is smaller than the current path depth
    // OR if depths are equal AND the current best sum is smaller than the current path sum
    if (isLeaf(node)) {
      if (depthSum.bestDepth < depth) {
        depthSum.bestDepth = depth;
        depthSum.bestSum = sum;
      } else if (depthSum.bestDepth == depth && depthSum.bestSum < sum) {
        depthSum.bestSum = sum;
      }
      return;
    }

    dfs(node.left, depthSum, depth, sum);
    dfs(node.right, depthSum, depth, sum);
  }

  /**
   * Count root-to-leaf paths
   * How many distinct paths exist from root to any leaf?
   */

  static int countUniquePaths(TreeNode<Integer> node) {
    if (node == null) {
      return 0;
    }
    if (isLeaf(node)) {
      return 1;

    }
    int l = countUniquePaths(node.left);
    int r = countUniquePaths(node.right);
    return l + r;
  }

  /**
   * Maximum root-to-leaf sum
   * Return the maximum possible sum from the root down to any leaf.
   */
  static int maxRootToLeafSum(TreeNode<Integer> root) {
    var ds = new DepthSum();

    maxPathSum(root, ds, 0);
    return ds.bestSum;
  }

  private static void maxPathSum(TreeNode<Integer> node, DepthSum max, int pathSum) {
    if (node == null) {
      return;
    }
    pathSum += node.val;
    if (isLeaf(node)) {
      if (max.bestSum < pathSum) {
        max.bestSum = pathSum;
      }
    }

    maxPathSum(node.left, max, pathSum);
    maxPathSum(node.right, max, pathSum);
  }

  /**
   * Check if a value exists on every root-to-leaf path
   * If any path misses it → false.
   */
  static boolean appearsOnEveryPath(TreeNode<Integer> node, int x) {
    if (node == null) {
      return false;
    }
    // at leaf path ends, return recorded state
    if (isLeaf(node)) {
      return node.val == x;
    }

    boolean left = appearsOnEveryPath(node.left, x);
    boolean right = appearsOnEveryPath(node.right, x);

    // if current == x, done checking
    if (node.val == x) {
      return true;
    }

    return left && right;
  }

  /**
   * O(n)
   */
  static boolean isBalanced(TreeNode<Integer> root) {
    return height(root) != -1;
  }

  static int height(TreeNode<Integer> node) {
    if (node == null) {
      return 0;
    }

    int left = height(node.left);
    if (left == -1) {
      return -1; // left subtree already unbalanced
    }

    int right = height(node.right);
    if (right == -1) {
      return -1; // right subtree already unbalanced
    }

    if (Math.abs(left - right) > 1) {
      return -1; // current node unbalanced
    }

    return Math.max(left, right) + 1;
  }

  /**
   * true if two binary trees: Have the same structure Contain the same values at corresponding
   * positions
   */
  static boolean areIdentical(TreeNode<Integer> a, TreeNode<Integer> b) {
    if (a == null && b == null) {
      return true;
    }
    
    return false;
  }

  /// ____________________________ HELPERS ____________________________

  // no children
  private static boolean isLeaf(TreeNode<?> node) {
    return node.left == null && node.right == null;
  }

  // at least one child
  private static boolean isInternal(TreeNode<?> node) {
    return node.left != null || node.right != null;
  }

  // both children exist
  private static boolean isFull(TreeNode<?> node) {
    return node.left != null && node.right != null;
  }
}
