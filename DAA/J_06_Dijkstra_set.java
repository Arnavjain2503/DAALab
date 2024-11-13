package DSA.DAA;

import java.util.*;

class Solution1 {
    // Function to find the shortest distance of all the vertices
    // from the source vertex S.
    public int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        // Create a TreeSet for storing the nodes as a pair {dist, node}
        // where dist is the distance from source to the node.
        // TreeSet stores the nodes in ascending order of the distances
        TreeSet<Pair> set = new TreeSet<>((p1, p2) -> {
            if (p1.dist != p2.dist) {
                return Integer.compare(p1.dist, p2.dist);
            }
            return Integer.compare(p1.node, p2.node);
        });

        // Initializing dist array with a large number to
        // indicate the nodes are unvisited initially.
        // This array contains distance from source to the nodes.
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Source initialized with dist=0
        dist[S] = 0;
        set.add(new Pair(0, S));

        // Now, remove the minimum distance node first from the set
        // and traverse for all its adjacent nodes.
        while (!set.isEmpty()) {
            Pair it = set.pollFirst();
            int node = it.node;
            int dis = it.dist;

            // Check for all adjacent nodes of the removed
            // element whether the prev dist is larger than current or not.
            for (ArrayList<Integer> edge : adj.get(node)) {
                int adjNode = edge.get(0);
                int edgeWeight = edge.get(1);

                if (dis + edgeWeight < dist[adjNode]) {
                    // Erase if it was visited previously at a greater cost.
                    set.remove(new Pair(dist[adjNode], adjNode));

                    // If current distance is smaller push it into the TreeSet
                    dist[adjNode] = dis + edgeWeight;
                    set.add(new Pair(dist[adjNode], adjNode));
                }
            }
        }
        // Return the array containing shortest distance from source to all the nodes.
        return dist;
    }

    // Helper class to represent a node and its distance in the TreeSet
    class Pair {
        int dist;
        int node;

        public Pair(int dist, int node) {
            this.dist = dist;
            this.node = node;
        }
    }
}

public class J_06_Dijkstra_set {
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

        Solution1 obj = new Solution1();
        int[] res = obj.dijkstra(V, adj, S);

        System.out.println("Shortest distances from source vertex " + S + ":");
        for (int i = 0; i < V; i++) {
            if (i == S) {
                System.out.println(S + " to " + i + " is " + res[i] + " (it's the source).");
            }
            else {
                System.out.println(S + " to " + i + " is " + res[i] + ".");
            }
        }
        sc.close();
    }
}
