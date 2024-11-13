package DSA.DAA;

import java.util.*;

class Pairr {
    int node;
    int distance;
    public Pairr(int distance, int node) {
        this.node = node;
        this.distance = distance;
    }
}

// User function Template for Java
class Solution {
    // Function to find the shortest distance of all the vertices from the source vertex S.
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S, ArrayList<Integer> visitOrder, int[] parent) {
        // Create a priority queue for storing the nodes as a pair {dist, node} where dist is the distance from source to the node.
        PriorityQueue<Pairr> pq = new PriorityQueue<Pairr>((x, y) -> x.distance - y.distance);

        boolean[] sptSet = new boolean[V];
        int[] key = new int[V]; // Track vertices included in the shortest path tree set

        Arrays.fill(key, Integer.MAX_VALUE);  // Initialize distances to a large value
        Arrays.fill(sptSet, false);
        // Initialize sptSet to false
        Arrays.fill(parent, -1);
        //Initialize parent to -1

        key[S] = 0;
        pq.add(new Pairr(0, S));

        // Now, pop the minimum distance node first from the min-heap
        // and traverse for all its adjacent nodes.
        while (!pq.isEmpty()) {
            int dis = pq.peek().distance;
            int node = pq.peek().node;
            pq.poll();

            // If the node is already in sptSet, continue
            if (sptSet[node] == true) {
                continue;
            }

            // Mark this node as included in sptSet
            sptSet[node] = true;
            visitOrder.add(node); // Record the order of node visitation

            // Check for all adjacent nodes of the popped-out element
            // whether the previous dist is larger than the current or not.
            for (int i = 0; i < adj.get(node).size(); i++) {
                int adjNode = adj.get(node).get(i).get(0);
                int edgeWeight = adj.get(node).get(i).get(1);

                // If current distance is smaller,
                // push it into the queue.
                if (sptSet[adjNode] == false && dis + edgeWeight < key[adjNode]) {
                    key[adjNode] = dis + edgeWeight;
                    parent[adjNode] = node;
                    pq.add(new Pairr(key[adjNode], adjNode));
                }
            }
        }
        return key;
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

public class J_06_Dijkstra_pq {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices (V): ");
        int V = sc.nextInt();

        System.out.print("Enter the number of edges (E): ");
        int E = sc.nextInt();

        sc.nextLine();

        HashMap<String, Integer> vertexMap = new HashMap<>();
        HashMap<Integer, String> reverseVertexMap = new HashMap<>();

        System.out.println("Enter the vertex names:");
        for (int i = 0; i < V; i++) {
            String vertex = sc.nextLine();
            vertexMap.put(vertex, i);
            reverseVertexMap.put(i, vertex);
        }

        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        System.out.println("Enter the edges in the format: source destination weight");
        for (int i = 0; i < E; i++) {
            String uLabel = sc.next();
            String vLabel = sc.next();
            int w = sc.nextInt();
            sc.nextLine();

            int u = vertexMap.get(uLabel);
            int v = vertexMap.get(vLabel);

            adj.get(u).add(new ArrayList<>(Arrays.asList(v, w)));
            adj.get(v).add(new ArrayList<>(Arrays.asList(u, w))); // For undirected graph
        }

        System.out.print("Enter the source vertex (S): ");
        String SLabel = sc.nextLine();
        int S = vertexMap.get(SLabel);

        ArrayList<Integer> visitOrder = new ArrayList<>(); // To store the order of visited vertices

        Solution obj = new Solution();
        int[] parent = new int[V];  // Array to track the previous nodes
        int[] res = obj.dijkstra(V, adj, S, visitOrder, parent);

        System.out.println("Shortest distances from source vertex " + SLabel + ":");
        for (int i = 0; i < V; i++) {
            String vertex = reverseVertexMap.get(i);
            if (i == S) {
                System.out.println(SLabel + " to " + vertex + " is " + res[i] + " (it's the source)");
            } else {
                System.out.print(SLabel + " to " + vertex + " is " + res[i] + ". Path: ");
                obj.printPath(i, parent);
                System.out.println();
            }
        }

        // Print the sptSet as an array in the order they were visited
        System.out.println("\nOrder of vertices visited and added to sptSet as an array:");
        for (int i = 0; i < visitOrder.size(); i++) {
            System.out.print(reverseVertexMap.get(visitOrder.get(i)) + " ,");
        }
        sc.close();
    }
}