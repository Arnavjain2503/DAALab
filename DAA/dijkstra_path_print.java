package DSA.DAA;

import java.util.*;

class Pairrr {
    int node;
    int distance;

    public Pairrr(int distance, int node) {
        this.node = node;
        this.distance = distance;
    }
}

// User function Template for Java
class Solutionn {
    // Function to find the shortest distance of all the vertices
    // from the source vertex S.
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S, int[] parent) {
        // Create a priority queue for storing the nodes as a pair {dist, node
        // where dist is the distance from source to the node.
        PriorityQueue<Pairrr> pq = new PriorityQueue<Pairrr>((x, y) -> x.distance - y.distance);

        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9);  // Initialize distances to a large value
        Arrays.fill(parent, -1);  // Initialize the previous node array

        dist[S] = 0;
        pq.add(new Pairrr(0, S));

        // Now, pop the minimum distance node first from the min-heap
        // and traverse for all its adjacent nodes.
        while (!pq.isEmpty()) {
            int dis = pq.peek().distance;
            int node = pq.peek().node;
            pq.poll();

            // Check for all adjacent nodes of the popped out
            // element whether the prev dist is larger than current or not.
            for (int i = 0; i < adj.get(node).size(); i++) {
                int edgeWeight = adj.get(node).get(i).get(1);
                int adjNode = adj.get(node).get(i).get(0);

                // If current distance is smaller,
                // push it into the queue.
                if (dis + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = dis + edgeWeight;
                    parent[adjNode] = node; // Track the previous node
                    pq.add(new Pairrr(dist[adjNode], adjNode));
                }
            }
        }
        return dist;
    }

    // Function to print the path from source to the destination node
    static void printPath(int node, int[] parent) {
        if (node == -1) {
            return;
        }
        printPath(parent[node], parent);
        System.out.print(node + " ");
    }
}

public class dijkstra_path_print {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices (V): ");
        int V = sc.nextInt();

        System.out.print("Enter the number of edges (E): ");
        int E = sc.nextInt();

        System.out.print("Enter the source vertex (S): ");
        int S = sc.nextInt();

        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        System.out.println("Enter the edges in the format: source destination weight");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            adj.get(u).add(new ArrayList<>(Arrays.asList(v, w)));
            adj.get(v).add(new ArrayList<>(Arrays.asList(u, w))); // For undirected graph
        }

        Solutionn obj = new Solutionn();
        int[] parent = new int[V];  // Array to track the previous nodes
        int[] res = obj.dijkstra(V, adj, S, parent);

        System.out.println("Shortest distances and paths from source vertex " + S + ":");
        for (int i = 0; i < V; i++) {
            System.out.print(S + " to " + i + " is " + res[i] + ". Path: ");
            obj.printPath(i, parent);
            System.out.println();
        }
        sc.close();
    }
}
