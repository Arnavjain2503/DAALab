package DSA.DAA;
import java.util.*;

class Edge5 implements Comparable<Edge5> {
    int src, dest, weight;
    public Edge5(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge5 other) {
        return this.weight - other.weight; // Ascending order by weight
    }
}

public class KruskalAlgorithm {
    // Function to create and initialize disjoint set
    void makeSet(int[] parent, int[] rank, int n) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    // Function to find the parent of a node (with path compression)
    int findParent(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent, parent[node]);
        }
        return parent[node];
    }
    // Function to union two sets
    void unionSet(int u, int v, int[] parent, int[] rank) {
        int rootU = findParent(parent, u);
        int rootV = findParent(parent, v);

        if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        }
        else if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        }
        else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
    }
    // Kruskal's algorithm to find the Minimum Spanning Tree
    int minimumSpanningTree(ArrayList<Edge5> edges, int n, List<Edge5> mstEdges) {
        Collections.sort(edges); // Sort edges using Comparable

        int[] parent = new int[n];
        int[] rank = new int[n];

        makeSet(parent, rank, n);

        int minWeight = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge5 edge = edges.get(i);
            int u = findParent(parent, edge.src);
            int v = findParent(parent, edge.dest);

            if (u != v) {
                minWeight += edge.weight;
                mstEdges.add(edge); // Add edge to MST
                unionSet(u, v, parent, rank);
            }
        }
        return minWeight;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices and edges
        System.out.println("Enter the number of vertices:");
        int V = sc.nextInt();
        System.out.println("Enter the number of edges:");
        int E = sc.nextInt();

        // Using HashMaps to handle any type of vertices (strings, random numbers)
        HashMap<String, Integer> vmap = new HashMap<>();
        HashMap<Integer, String> imap = new HashMap<>();
        ArrayList<Edge5> edges = new ArrayList<>();

        System.out.println("Enter all the vertices:");
        for (int i = 0; i < V; i++) {
            String vertex = sc.next();
            vmap.put(vertex, i);
            imap.put(i, vertex);
        }

        System.out.println("Enter edges in the format <src> <dest> <weight>:");
        for (int i = 0; i < E; i++) {
            String src = sc.next();
            String dest = sc.next();
            int weight = sc.nextInt();
            edges.add(new Edge5(vmap.get(src), vmap.get(dest), weight));
        }

        // Kruskal's algorithm
        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        List<Edge5> mstEdges = new ArrayList<>();
        int minWeight = kruskal.minimumSpanningTree(edges, V, mstEdges);

        // Output the MST edges
        System.out.println("Minimum Spanning Tree edges:");
        for (int i = 0; i < mstEdges.size(); i++) {
            Edge5 edge = mstEdges.get(i);
            System.out.println(imap.get(edge.src) + " - " + imap.get(edge.dest) + " with weight " + edge.weight);
        }

        System.out.println("Total weight of MST: " + minWeight);
    }
}