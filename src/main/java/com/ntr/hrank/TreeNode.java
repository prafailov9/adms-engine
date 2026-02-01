package com.ntr.hrank;

public class TreeNode<T> {

  public T val;
  public TreeNode<T> left;
  public TreeNode<T> right;

  public TreeNode(T val) {
    this(val, null, null);
  }

  public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    return "TreeNode{" +
        "val=" + val +
        '}';
  }
}
