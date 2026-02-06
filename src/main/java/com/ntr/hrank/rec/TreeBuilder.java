package com.ntr.hrank.rec;

import com.ntr.hrank.TreeNode;
import java.util.ArrayDeque;
import java.util.Queue;

public class TreeBuilder {

  static TreeNode<Integer> fromLevelOrder(Integer... values) {
    if (values.length == 0 || values[0] == null) {
      return null;
    }

    TreeNode<Integer> root = new TreeNode<>(values[0]);
    Queue<TreeNode<Integer>> q = new ArrayDeque<>();
    q.add(root);

    int i = 1;
    while (i < values.length && !q.isEmpty()) {
      TreeNode<Integer> curr = q.poll();

      if (values[i] != null) {
        curr.left = new TreeNode<>(values[i]);
        q.add(curr.left);
      }
      i++;

      if (i < values.length && values[i] != null) {
        curr.right = new TreeNode<>(values[i]);
        q.add(curr.right);
      }
      i++;
    }

    return root;
  }

  static TreeNode<Integer> perfect(int height) {
    return build(1, height);
  }

  private static TreeNode<Integer> build(int val, int height) {
    if (height == 0) {
      return null;
    }
    return new TreeNode<>(val, build(val * 2, height - 1), build(val * 2 + 1, height - 1));
  }

  static TreeNode<Integer> random(int depth) {
    if (depth == 0 || Math.random() < 0.3) {
      return null;
    }

    return new TreeNode<>(
        (int) (Math.random() * 10),
        random(depth - 1),
        random(depth - 1)
    );
  }
}
