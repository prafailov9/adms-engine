package com.ntr.hrank;

import java.util.*;

public class CycleDetectionUndirected {

  public static boolean hasCycle(int n, List<List<Integer>> graph) {
    boolean[] visited = new boolean[n];

    // Graph may be disconnected
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        if (dfs(i, -1, visited, graph)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean dfs(int currentNodeIndex, int parent,
      boolean[] visited,
      List<List<Integer>> graph) {

    visited[currentNodeIndex] = true;

    for (int neighbor : graph.get(currentNodeIndex)) {

      if (!visited[neighbor]) {
        if (dfs(neighbor, currentNodeIndex, visited, graph)) {
          return true;
        }
      }
      // visited neighbor that is NOT the parent → cycle
      else if (neighbor != parent) {
        return true;
      }
    }
    return false;
  }

  // Example usage
  public static void main(String[] args) {
    int n = 4;
    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    // add undirected edges
    addEdge(graph, 0, 1);
    addEdge(graph, 1, 2);
    addEdge(graph, 2, 3);
    addEdge(graph, 3, 0); // creates a cycle

    System.out.println(hasCycle(n, graph)); // true
  }

  private static void addEdge(List<List<Integer>> graph, int u, int v) {
    graph.get(u).add(v);
    graph.get(v).add(u);
  }
}
