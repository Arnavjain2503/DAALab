package DSA.DAA;
import java.util.*;

class Edge1 {
    int src, dest, wt;

    Edge1(int src, int dest, int wt) {
        this.src = src;
        this.dest = dest;
        this.wt = wt;
    }
}

class Pair implements Comparable<Pair> {
    int node, dist;

    Pair(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }

    @Override
    public int compareTo(Pair p2) {
        return this.dist - p2.dist; // Ascending order based on distance
    }
}

public class Dijkstra {
    // Method to create the graph based on user input
    public static void createGraph(ArrayList<Edge1>[] graph, Scanner sc, HashMap<String, Integer> vMap) {
        System.out.println("Enter the number of edges: ");
        int E = sc.nextInt(); // Number of edges
        System.out.println("Enter source, destination, and weight for edge : ");
        for (int i = 0; i < E; i++) {

            String srcStr = sc.next();
            String destStr = sc.next();
            int wt = sc.nextInt();

            // Map the vertices to indices
            int src = vMap.get(srcStr);
            int dest = vMap.get(destStr);

            // Since the graph is directed, add an edge from src to dest
            graph[src].add(new Edge1(src, dest, wt));
        }
    }

    // Dijkstra's algorithm implementation using PriorityQueue
    public static void dijkstra(ArrayList<Edge1>[] graph, int src, int V, HashMap<Integer, String> iMap) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[V];
        boolean[] vis = new boolean[V];

        // Initialize all distances as infinity
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove(); // Get the node with the shortest distance
            int u = curr.node;

            if (!vis[u]) {
                vis[u] = true; // Mark the current node as visited

                // Traditional for loop to iterate through the edges
                for (int i = 0; i < graph[u].size(); i++) {
                    Edge1 e = graph[u].get(i);
                    int v = e.dest;

                    // Relaxation step
                    if (dist[u] + e.wt < dist[v]) {
                        dist[v] = dist[u] + e.wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }

        // Print the shortest distances from the source node
        System.out.println("Shortest distances from node " + iMap.get(src) + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Node " + iMap.get(i) + " : " + dist[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices: ");
        int V = sc.nextInt(); // Number of vertices

        ArrayList<Edge1>[] graph = new ArrayList[V];
        HashMap<String, Integer> vMap = new HashMap<>();
        HashMap<Integer, String> iMap = new HashMap<>();

        // Input vertices
        System.out.println("Enter the vertices (e.g., A, B, C or 101, 102, 103): ");
        for (int i = 0; i < V; i++) {
            String vertex = sc.next();
            vMap.put(vertex, i);
            iMap.put(i, vertex);
            graph[i] = new ArrayList<>(); // Create a new ArrayList for each vertex
        }

        // Create the graph using user input
        createGraph(graph, sc, vMap);

        System.out.println("Enter the source vertex for Dijkstra's algorithm: ");
        String srcStr = sc.next();
        int src = vMap.get(srcStr);

        // Run Dijkstra's algorithm from the given source node
        dijkstra(graph, src, V, iMap);

        sc.close();  // Close the scanner to avoid resource leaks
    }
}