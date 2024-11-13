package DSA.DAA;
import java.util.*;

class Edge2 {
    int src, dest, wt;

    Edge2(int src, int dest, int wt) {
        this.src = src;
        this.dest = dest;
        this.wt = wt;
    }
}

public class prims {
    public static void createGraph(ArrayList<Edge2>[] graph, int V, int E, HashMap<String, Integer> vMap) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.println("Enter the edges in the format: source destination weight");
        for (int i = 0; i < E; i++) {
            String srcVertex = sc.next();
            String destVertex = sc.next();
            int wt = sc.nextInt();

            int src = vMap.get(srcVertex);
            int dest = vMap.get(destVertex);

            // Since the graph is undirected, add the edge in both directions
            graph[src].add(new Edge2(src, dest, wt));
            graph[dest].add(new Edge2(dest, src, wt));
        }
    }

    public static class Pair implements Comparable<Pair> {
        int node;
        int cost;

        public Pair(int n, int c) {
            this.node = n;
            this.cost = c;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost; // ascending order
        }
    }

    public static void primsAlgo(ArrayList<Edge2>[] graph, int V, HashMap<Integer, String> iMap) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[V];
        int[] parent = new int[V]; // Array to store the parent of each node
        int[] key = new int[V]; // Array to store the minimum weight to reach each node

        // Initialize the arrays
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        key[0] = 0; // Start from the first node
        pq.add(new Pair(0, 0)); // (node, cost)

        int mstCost = 0;

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();

            if (!vis[curr.node]) {
                vis[curr.node] = true;
                mstCost += curr.cost;

                // Process all adjacent vertices
                for (int i = 0; i < graph[curr.node].size(); i++) {
                    Edge2 e = graph[curr.node].get(i);
                    if (!vis[e.dest] && e.wt < key[e.dest]) {
                        key[e.dest] = e.wt;
                        parent[e.dest] = curr.node;
                        pq.add(new Pair(e.dest, e.wt));
                    }
                }
            }
        }

        System.out.println("Minimum cost of MST = " + mstCost);
        System.out.println("Edges in the MST:");
        for (int i = 1; i < V; i++) { // Start from 1 because 0 has no parent
            System.out.println(iMap.get(parent[i]) + " - " + iMap.get(i) + ": " + key[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int V = sc.nextInt();

        HashMap<String, Integer> vMap = new HashMap<>();
        HashMap<Integer, String> iMap = new HashMap<>();

        System.out.println("Enter the vertices:");
        for (int i = 0; i < V; i++) {
            String vertex = sc.next();
            vMap.put(vertex, i);
            iMap.put(i, vertex);
        }

        System.out.println("Enter the number of edges:");
        int E = sc.nextInt();

        ArrayList<Edge2>[] graph = new ArrayList[V];
        createGraph(graph, V, E, vMap);

        primsAlgo(graph, V,iMap);
    }
}