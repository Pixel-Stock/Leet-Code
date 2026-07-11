import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int complete = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            List<Integer> component = new ArrayList<>();
            dfs(i, graph, visited, component);

            int size = component.size();
            boolean isComplete = true;

            for (int node : component) {
                if (graph[node].size() != size - 1) {
                    isComplete = false;
                    break;
                }
            }

            if (isComplete) {
                complete++;
            }
        }

        return complete;
    }

    private void dfs(int node, List<Integer>[] graph, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, component);
            }
        }
    }
}