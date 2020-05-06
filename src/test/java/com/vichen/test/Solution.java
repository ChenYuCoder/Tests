package com.vichen.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 各种遍历方式
 */
public class Solution {
  public static List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null)
      return result;
    result.addAll(inorderTraversal(root.left));
    result.add(root.val);
    result.addAll(inorderTraversal(root.right));
    return result;
  }

  public static List<Integer> inorderTraversal4(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null)
      return result;
    result.add(root.val);
    result.addAll(inorderTraversal4(root.left));
    result.addAll(inorderTraversal4(root.right));
    return result;
  }

  public static List<Integer> inorderTraversal5(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null)
      return result;
    result.addAll(inorderTraversal4(root.left));
    result.addAll(inorderTraversal4(root.right));
    result.add(root.val);
    return result;
  }

  public static List<Integer> inorderTraversal2(TreeNode node) {
    if (node == null)
      return null;
    List<Integer> result = new ArrayList<>();
    TreeNode p = node;
    Stack<TreeNode> stack = new Stack<>();
    while (p != null || !stack.isEmpty()) {
      if (p != null) {
        stack.push(p);
        p = p.left;
      } else {
        p = stack.pop();
        result.add(p.val);
        p = p.right;
      }
    }
    return result;
  }

  public static List<Integer> inorderTraversal3(TreeNode node) {
    if (node == null)
      return null;
    List<Integer> result = new ArrayList<>();
    TreeNode p = node;
    Stack<TreeNode> stack = new Stack<>();
    while (p != null || !stack.isEmpty()) {
      if (p != null) {
        stack.add(p);
        result.add(p.val);
        p = p.left;
      } else {
        p = stack.pop();
        p = p.right;
      }
    }
    return result;
  }

  public static List<Integer> inorderTraversal6(TreeNode node) {
    if (node == null)
      return null;
    List<Integer> result = new ArrayList<>();
    TreeNode p = node;
    Stack<TreeNode> stack = new Stack<>();
    while (p != null || !stack.isEmpty()) {
      if (p != null) {
        stack.add(p);
        p = p.left;
      } else {
        p = stack.pop();
        p = p.right;
      }
    }
    return result;
  }

  @Test public void test() {
    TreeNode root = new TreeNode(1);
    TreeNode right = new TreeNode(2);
    right.left = new TreeNode(4);
    right.right = new TreeNode(5);
    TreeNode left = new TreeNode(3);
    left.left = new TreeNode(6);
    left.right = new TreeNode(7);
    left.right.left = new TreeNode(8);
    root.right = right;
    root.left = left;
    System.out.println("\n--------------中序---------------");
    List<Integer> result = inorderTraversal(root);
    for (Integer i : result) {
      System.out.printf("" + i);
    }

    System.out.println("\n-----------------------------");
    List<Integer> result2 = inorderTraversal2(root);
    for (Integer i : result2) {
      System.out.printf("" + i);
    }


    System.out.println("\n------------先序-----------------");
    List<Integer> result4 = inorderTraversal4(root);
    for (Integer i : result4) {
      System.out.printf("" + i);
    }

    System.out.println("\n-----------------------------");
    List<Integer> result3 = inorderTraversal3(root);
    for (Integer i : result3) {
      System.out.printf("" + i);
    }

    System.out.println("\n------------后序-----------------");
    List<Integer> result5 = inorderTraversal5(root);
    for (Integer i : result5) {
      System.out.printf("" + i);
    }

    System.out.println("\n-----------------------------");
    List<Integer> result6 = inorderTraversal6(root);
    for (Integer i : result6) {
      System.out.printf("" + i);
    }
    System.out.println("\n-----------------------------");
  }


  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}

