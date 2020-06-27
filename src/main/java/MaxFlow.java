// https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MaxFlow {
    private static ArrayList<LinkedList<Edge>> graph;
    private static Edge[][] edgeRecord;
    private static int N;
    private static boolean[] visited;

    private static void addOrUpdateEdge(int u, int v, int capacity) {
        if (edgeRecord[u][v] == null) {
            edgeRecord[u][v] = new Edge(capacity, v);
            if (graph.get(u) == null) {
                graph.set(u, new LinkedList<>());
            }
            graph.get(u).add(edgeRecord[u][v]);
        } else {
            edgeRecord[u][v].capacity = capacity;
        }
    }

    private static void initializeGraph() {
        N = 6;
        graph = new ArrayList<>(N);
        edgeRecord = new Edge[N][N];
        for (int i = 0; i < N; i++) {
            graph.add(new LinkedList<>());
        }
        addOrUpdateEdge(0, 1, 16);
        addOrUpdateEdge(0, 2, 13);
        addOrUpdateEdge(1, 2, 10);
        addOrUpdateEdge(1, 3, 12);
        addOrUpdateEdge(2, 1, 4);
        addOrUpdateEdge(2, 4, 14);
        addOrUpdateEdge(3, 2, 9);
        addOrUpdateEdge(3, 5, 20);
        addOrUpdateEdge(4, 3, 7);
        addOrUpdateEdge(4, 5, 4);
        visited = new boolean[N];
    }

    /**
     * @param s      Source vertex
     * @param t      Sink vertex
     * @param parent array to store parent node of each vertex during travel
     * @return Sink vertex reached
     */
    public static boolean bfs(int s, int t, int[] parent) {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(visited, false);
        q.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!q.isEmpty()) {
            int current = q.poll();
            if (graph.get(current) != null) {
                for (Edge e : graph.get(current)) {
                    if (!visited[e.destination] && e.capacity > 0) {
                        q.add(e.destination);
                        parent[e.destination] = current;
                        visited[e.destination] = true;
                    }
                }
            }
        }
        return visited[t];
    }

    /**
     * @param s Source vertex
     * @param t Sink vertex
     * @return max-flow in the graph
     */
    public static int fordFulkerson(int s, int t) {
        int maxFlow = 0;
        int[] parent = new int[N];
        while (bfs(s, t, parent)) {
            int pathFlow = Integer.MAX_VALUE;

            //Iterate path traveled in last bfs
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                int capacity = edgeRecord[u][v].capacity;
                pathFlow = Math.min(pathFlow, capacity);
            }

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                int capacityUToV = edgeRecord[u][v].capacity;
                int capacityVToU = edgeRecord[v][u] != null ? edgeRecord[v][u].capacity : 0;
                addOrUpdateEdge(u, v, capacityUToV - pathFlow);
                addOrUpdateEdge(v, u, capacityVToU + pathFlow);
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    public static void main(String[] args) {
        initializeGraph();
        System.out.println(fordFulkerson(0, 5));
    }

    public static class Edge {
        int capacity;
        int destination;

        public Edge(int capacity, int destination) {
            this.capacity = capacity;
            this.destination = destination;
        }
    }
}