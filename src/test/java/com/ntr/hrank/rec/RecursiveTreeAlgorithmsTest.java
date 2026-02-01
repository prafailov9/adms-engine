package com.ntr.hrank.rec;

import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.allEven;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.contains;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.countFullNodes;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.countInternalNodes;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.countLeaves;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.countNodes;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.countSingleChildNodes;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.findMax;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.heightDifference;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.isChain;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.maxDepth;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.sumLeaves;
import static com.ntr.hrank.rec.RecursiveTreeAlgorithms.treeSum;
import static org.junit.jupiter.api.Assertions.*;

import com.ntr.hrank.TreeNode;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class RecursiveTreeAlgorithmsTest {

  @Test
  public void maxDepthTest() {
    int res;
    System.out.println("null root:");
    res = maxDepth(null);
    assertEquals(0, res);

    System.out.println("only root:");
    res = maxDepth(new TreeNode<>(1));
    assertEquals(1, res);

    System.out.println("only right side:");
    res = maxDepth(new TreeNode<>(1, null, new TreeNode<>(2, null, new TreeNode<>(3))));
    assertEquals(3, res);

    System.out.println("balanced:");
    res = maxDepth(new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4), new TreeNode<>(5)),
        new TreeNode<>(3, new TreeNode<>(6), new TreeNode<>(7))));
    assertEquals(3, res);

    System.out.println("Unbalanced:");
    res = maxDepth(
        new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4, new TreeNode<>(5), null), null),
            new TreeNode<>(3)));
    assertEquals(4, res);

    System.out.println("Root with only one child");
    res = maxDepth(new TreeNode<>(1, new TreeNode<>(2), null));
    assertEquals(2, res);

    System.out.println("Unbalanced#2");
    TreeNode<Integer> n4 = new TreeNode<>(5);
    TreeNode<Integer> n3 = new TreeNode<>(4);
    TreeNode<Integer> n2 = new TreeNode<>(3, n3, n4);
    TreeNode<Integer> n1 = new TreeNode<>(2);
    TreeNode<Integer> root = new TreeNode<>(1, n1, n2);

    res = maxDepth(root);
    assertEquals(3, res);
  }

  @Test
  public void findMaxTest() {
    int res;
    System.out.println("null root:");
    res = findMax(null);
    assertEquals(Integer.MIN_VALUE, res);

    System.out.println("only root:");
    res = findMax(new TreeNode<>(7));
    assertEquals(7, res);

    System.out.println("Simple tree:");
    res = findMax(new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3)));
    assertEquals(3, res);

    System.out.println("Unbalanced:");
    res = findMax(
        new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4, new TreeNode<>(5), null), null),
            new TreeNode<>(3)));
    assertEquals(5, res);

    System.out.println("Tree with negatives");
    res = findMax(
        new TreeNode<>(-10, new TreeNode<>(-20), new TreeNode<>(-3, null, new TreeNode<>(-1))));
    assertEquals(-1, res);

    System.out.println("Max at rooot:");
    res = findMax(new TreeNode<>(100, new TreeNode<>(20), new TreeNode<>(30)));
    assertEquals(100, res);

    System.out.println("All are equal:");
    res = findMax(new TreeNode<>(5, new TreeNode<>(5), new TreeNode<>(5)));
    assertEquals(5, res);
  }


  @Test
  public void countNodesTest() {
    int res;
    System.out.println("null root:");
    res = countNodes(null);
    assertEquals(0, res);

    System.out.println("Single node:");
    res = countNodes(new TreeNode<>(7));
    assertEquals(1, res);

    System.out.println("Simple tree:");
    res = countNodes(new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3)));
    assertEquals(3, res);

    System.out.println("Unbalanced:");
    res = countNodes(
        new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4, new TreeNode<>(5), null), null),
            new TreeNode<>(3)));
    assertEquals(5, res);

    System.out.println("Right-heavy tree");
    res = countNodes(
        new TreeNode<>(1, null, new TreeNode<>(2, null, new TreeNode<>(3))));
    assertEquals(3, res);

    System.out.println("Larger tree:");
    res = countNodes(new TreeNode<>(10, new TreeNode<>(5),
        new TreeNode<>(20, new TreeNode<>(15), new TreeNode<>(30))));
    assertEquals(5, res);

    System.out.println("All are equal:");
    res = countNodes(new TreeNode<>(5, new TreeNode<>(5), new TreeNode<>(5)));
    assertEquals(3, res);
  }

  @Test
  public void treeSumTest() {
    int res;
    System.out.println("null root:");
    res = treeSum(null);
    assertEquals(0, res);

    System.out.println("Single node:");
    res = treeSum(new TreeNode<>(7));
    assertEquals(7, res);

    System.out.println("Simple tree:");
    res = treeSum(new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3)));
    assertEquals(6, res);

    System.out.println("Unbalanced:");
    res = treeSum(
        new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4, new TreeNode<>(5), null), null),
            new TreeNode<>(9)));
    assertEquals(21, res);

    System.out.println("Right-heavy tree");
    res = treeSum(
        new TreeNode<>(1, null, new TreeNode<>(3, null, new TreeNode<>(3))));
    assertEquals(7, res);

    System.out.println("Larger tree:");
    res = treeSum(new TreeNode<>(10, new TreeNode<>(5),
        new TreeNode<>(20, new TreeNode<>(15), new TreeNode<>(30))));
    assertEquals(80, res);

    System.out.println("All are equal:");
    res = treeSum(new TreeNode<>(5, new TreeNode<>(0), new TreeNode<>(5)));
    assertEquals(10, res);
  }

  @Test
  public void containsTest() {
    boolean res;
    System.out.println("null root:");
    assertFalse(contains(null, 0));

    System.out.println("Single node:");
    res = contains(new TreeNode<>(7), 7);
    assertTrue(res);

    System.out.println("Simple tree:");
    res = contains(new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3)), 2);
    assertTrue(res);

    System.out.println("Unbalanced:");
    res = contains(
        new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4, new TreeNode<>(5), null), null),
            new TreeNode<>(9)), 9);
    assertTrue(res);

    System.out.println("Right-heavy tree");
    res = contains(
        new TreeNode<>(1, null, new TreeNode<>(3, null, new TreeNode<>(3))), 3);
    assertTrue(res);

    System.out.println("Larger tree:");
    res = contains(new TreeNode<>(10, new TreeNode<>(5),
        new TreeNode<>(20, new TreeNode<>(15), new TreeNode<>(30))), 30);
    assertTrue(res);

    System.out.println("All are equal:");
    res = contains(new TreeNode<>(5, new TreeNode<>(0), new TreeNode<>(5)), 5);
    assertTrue(res);
  }


  @Test
  public void countLeavesTest() {
    int res;
    System.out.println("null root:");
    res = countLeaves(null);
    assertEquals(0, res);

    System.out.println("Single node:");
    res = countLeaves(new TreeNode<>(7));
    assertEquals(1, res);

    System.out.println("Simple tree:");
    res = countLeaves(new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3)));
    assertEquals(2, res);

    System.out.println("Unbalanced:");
    res = countLeaves(
        new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4, new TreeNode<>(5), null), null),
            new TreeNode<>(9)));
    assertEquals(2, res);

    System.out.println("Right-heavy tree");
    res = countLeaves(
        new TreeNode<>(1, null, new TreeNode<>(3, null, new TreeNode<>(3))));
    assertEquals(1, res);

    System.out.println("Larger tree:");
    res = countLeaves(new TreeNode<>(10, new TreeNode<>(5),
        new TreeNode<>(20, new TreeNode<>(15), new TreeNode<>(30))));
    assertEquals(3, res);

    System.out.println("All are equal:");
    res = countLeaves(new TreeNode<>(5, new TreeNode<>(0), new TreeNode<>(5)));
    assertEquals(2, res);
  }

  @Test
  public void allEvenTest() {
    boolean res;
    System.out.println("null root:");
    assertTrue(allEven(null));

    System.out.println("Single node:");
    res = allEven(new TreeNode<>(7));
    assertFalse(res);

    System.out.println("Simple tree:");
    res = allEven(new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3)));
    assertFalse(res);

    System.out.println("Unbalanced:");
    res = allEven(
        new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4, new TreeNode<>(5), null), null),
            new TreeNode<>(9)));
    assertFalse(res);

    System.out.println("Right-heavy tree");
    res = allEven(
        new TreeNode<>(1, null, new TreeNode<>(2, null, new TreeNode<>(2))));
    assertFalse(res);

    System.out.println("Larger tree:");
    res = allEven(new TreeNode<>(10, new TreeNode<>(8),
        new TreeNode<>(20, new TreeNode<>(30), new TreeNode<>(120))));
    assertTrue(res);

    System.out.println("All are equal:");
    res = allEven(new TreeNode<>(10, new TreeNode<>(0), new TreeNode<>(80)));
    assertTrue(res);

    System.out.println("All are equal:");
    res = allEven(new TreeNode<>(2, new TreeNode<>(8), new TreeNode<>(8, new TreeNode<>(7), null)));
    assertFalse(res);
  }

  @Test
  public void heightDifferenceTest() {
    int res;
    System.out.println("null root:");
    res = heightDifference(null);
    assertEquals(0, res);

    System.out.println("Single node:");
    res = heightDifference(new TreeNode<>(7));
    assertEquals(0, res);

    System.out.println("Simple tree:");
    res = heightDifference(new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3)));
    assertEquals(0, res);

    System.out.println("Unbalanced:");
    res = heightDifference(
        new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4, new TreeNode<>(5), null), null),
            new TreeNode<>(9)));
    assertEquals(2, res);

    System.out.println("Right-heavy tree");
    res = heightDifference(
        new TreeNode<>(1, null, new TreeNode<>(3, null, new TreeNode<>(3))));
    assertEquals(2, res);

    System.out.println("Larger tree:");
    res = heightDifference(new TreeNode<>(10, new TreeNode<>(5),
        new TreeNode<>(20, new TreeNode<>(15), new TreeNode<>(30))));
    assertEquals(1, res);

    System.out.println("All are equal:");
    res = heightDifference(new TreeNode<>(5, new TreeNode<>(0), new TreeNode<>(5)));
    assertEquals(0, res);
  }

  @Test
  public void countInternalNodesTest() {
    int res;
    System.out.println("null root:");
    res = countInternalNodes(null);
    assertEquals(0, res);

    System.out.println("Single node:");
    res = countInternalNodes(new TreeNode<>(7));
    assertEquals(0, res);

    System.out.println("Simple tree:");
    res = countInternalNodes(new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3)));
    assertEquals(1, res);

    System.out.println("Unbalanced:");
    res = countInternalNodes(
        new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4, new TreeNode<>(5), null), null),
            new TreeNode<>(9)));
    assertEquals(3, res);

    System.out.println("Right-heavy tree");
    res = countInternalNodes(
        new TreeNode<>(1, null, new TreeNode<>(3, null, new TreeNode<>(3))));
    assertEquals(2, res);

    System.out.println("Larger tree:");
    res = countInternalNodes(new TreeNode<>(10, new TreeNode<>(5),
        new TreeNode<>(20, new TreeNode<>(15), new TreeNode<>(30))));
    assertEquals(2, res);

    System.out.println("All are equal:");
    res = countInternalNodes(new TreeNode<>(5, new TreeNode<>(0), new TreeNode<>(5)));
    assertEquals(1, res);
  }

  @Test
  public void sumLeavesTest() {
    int res;
    System.out.println("null root:");
    res = sumLeaves(null);
    assertEquals(0, res);

    System.out.println("Single node:");
    res = sumLeaves(new TreeNode<>(7));
    assertEquals(7, res);

    System.out.println("Simple tree:");
    res = sumLeaves(new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3)));
    assertEquals(5, res);

    System.out.println("Unbalanced:");
    res = sumLeaves(
        new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4, new TreeNode<>(5), null), null),
            new TreeNode<>(9)));
    assertEquals(14, res);

    System.out.println("Right-heavy tree");
    res = sumLeaves(
        new TreeNode<>(1, null, new TreeNode<>(3, null, new TreeNode<>(3))));
    assertEquals(3, res);

    System.out.println("Large tree:");
    res = sumLeaves(new TreeNode<>(10, new TreeNode<>(5),
        new TreeNode<>(20, new TreeNode<>(15), new TreeNode<>(30))));
    assertEquals(50, res);

    System.out.println("All are equal:");
    res = sumLeaves(new TreeNode<>(5, new TreeNode<>(0), new TreeNode<>(5)));
    assertEquals(5, res);
  }

  @Test
  public void isChainTest() {
    boolean res;
    System.out.println("null root:");
    assertTrue(isChain(null));

    System.out.println("Single node:");
    res = isChain(new TreeNode<>(7));
    assertTrue(res);

    System.out.println("Simple tree:");
    res = isChain(new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3)));
    assertFalse(res);

    System.out.println("Unbalanced:");
    res = isChain(
        new TreeNode<>(1, new TreeNode<>(2, new TreeNode<>(4, new TreeNode<>(5), null), null),
            new TreeNode<>(9)));
    assertFalse(res);

    System.out.println("Right-heavy tree");
    res = isChain(
        new TreeNode<>(1, null, new TreeNode<>(2, null, new TreeNode<>(2))));
    assertTrue(res);

    System.out.println("Larger tree:");
    res = isChain(new TreeNode<>(10, new TreeNode<>(8),
        new TreeNode<>(20, new TreeNode<>(30), new TreeNode<>(120))));
    assertFalse(res);

    System.out.println("All are equal:");
    res = isChain(new TreeNode<>(10, new TreeNode<>(0), new TreeNode<>(80)));
    assertFalse(res);

    System.out.println("All are equal:");
    res = isChain(new TreeNode<>(2, new TreeNode<>(8), new TreeNode<>(8, new TreeNode<>(7), null)));
    assertFalse(res);
  }

  @Test
  public void countSingleChildNodesTest() {
    int res;
    System.out.println("null root:");
    res = countSingleChildNodes(null);
    assertEquals(0, res);

    System.out.println("Single node:");
    res = countSingleChildNodes(new TreeNode<>(7));
    assertEquals(0, res);

    System.out.println("Simple tree:");
    res = countSingleChildNodes(new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3)));
    assertEquals(0, res);

    System.out.println("Unbalanced:");
    res = countSingleChildNodes(
        new TreeNode<>(1,
            new TreeNode<>(2,
                new TreeNode<>(4, new TreeNode<>(5),
                    null),
                null),
            new TreeNode<>(9)));
    assertEquals(2, res);

    System.out.println("Right-heavy tree");
    res = countSingleChildNodes(
        new TreeNode<>(1, null, new TreeNode<>(3, null, new TreeNode<>(3))));
    assertEquals(2, res);

    System.out.println("Large tree:");
    res = countSingleChildNodes(new TreeNode<>(10, new TreeNode<>(5),
        new TreeNode<>(20, new TreeNode<>(15), new TreeNode<>(30))));
    assertEquals(0, res);

    System.out.println("All are equal:");
    res = countSingleChildNodes(new TreeNode<>(5, new TreeNode<>(0), new TreeNode<>(5)));
    assertEquals(0, res);
  }

  @Test
  public void countFullNodesTest() {
    int res;

    System.out.println("Null root");
    res = countFullNodes(null);
    assertEquals(0, res);

    System.out.println("Single node");
    res = countFullNodes(new TreeNode<>(1));
    assertEquals(0, res);

    System.out.println("Root with two children");
    res = countFullNodes(
        new TreeNode<>(1, new TreeNode<>(2), new TreeNode<>(3))
    );
    assertEquals(1, res);

    System.out.println("Root with one child");
    res = countFullNodes(
        new TreeNode<>(1, new TreeNode<>(2), null)
    );
    assertEquals(0, res);

    System.out.println("Balanced tree");
    res = countFullNodes(
        new TreeNode<>(1,
            new TreeNode<>(2, new TreeNode<>(4), new TreeNode<>(5)),
            new TreeNode<>(3, new TreeNode<>(6), new TreeNode<>(7)))
    );
    // nodes: 1, 2, 3 all have two children
    assertEquals(3, res);

    System.out.println("Unbalanced tree");
    res = countFullNodes(
        new TreeNode<>(1,
            new TreeNode<>(2,
                new TreeNode<>(4), null),
            new TreeNode<>(3))
    );
    // only node 1 has two children
    assertEquals(1, res);

    System.out.println("Right-heavy tree");
    res = countFullNodes(
        new TreeNode<>(1, null,
            new TreeNode<>(2, null,
                new TreeNode<>(3)))
    );
    assertEquals(0, res);
  }


}