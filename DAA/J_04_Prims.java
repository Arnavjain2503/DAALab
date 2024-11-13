package DSA.DAA;

import java.util.*;

class Pair1 {
    int node;     // Node Number
    int weight;   // Weight or cost
    public Pair1(int weight, int node) {
        this.node = node;
        this.weight = weight;
    }
}

class PrimsSoln {
    // Function to find sum of weights of edges of the MST and print MST
    static void primsAlgo(ArrayList<Pair1>[] graph, int V, HashMap<Integer, String> reverseVertexMap) {
        PriorityQueue<Pair1> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);

        int[] vis = new int[V]; // To store visited nodes
        int[] parent = new int[V]; // To store the parent of each node in the MST (for printing MST)

        int[] key = new int[V]; // To store the minimum weight to reach each node (for printing MST)
        Arrays.fill(key, Integer.MAX_VALUE); // Initialize key array with maximum value

        key[0] = 0;  // Initialize 0 as no cost needed to start
        parent[0] = -1; // Parent -1 as it is the root node, so no parent
        pq.add(new Pair1(0, 0)); // {wt, node}

        int sum = 0;

        while (!pq.isEmpty()) {
            int wt = pq.peek().weight;
            int node = pq.peek().node;
            pq.remove();

            if (vis[node] == 1){
                continue;  // If visited, then skip it
            }
            // Add it to the MST, meaning marking it as visited
            vis[node] = 1;
            // Adding its weight
            sum += wt;

            for (Pair1 p : graph[node]) {
                int adjNode = p.node;
                int edW = p.weight;

                if (vis[adjNode] == 0 && edW < key[adjNode]) {
                    pq.add(new Pair1(edW, adjNode));
                    parent[adjNode] = node;  // Update parent of the adjacent node
                    key[adjNode] = edW;
                }
            }
        }
        // Printing the edges of the MST
        System.out.println("Edges in the MST:");
        for (int i = 1; i < V; i++) {
            String srcVertex = reverseVertexMap.get(parent[i]);
            String destVertex = reverseVertexMap.get(i);
            System.out.println(srcVertex + " - " + destVertex + " (Weight: " + key[i] + ")");
        }
        System.out.println("Sum of weights of edges of the MST: " + sum);
    }
}

public class J_04_Prims {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int V = sc.nextInt();

        System.out.println("Enter the number of edges:");
        int E = sc.nextInt();

        sc.nextLine();

        HashMap<String, Integer> vertexMap = new HashMap<>();
        HashMap<Integer, String> reverseVertexMap = new HashMap<>();

        System.out.println("Enter the vertex names:");
        for (int i = 0; i < V; i++) {
            String vertexName = sc.nextLine();
            vertexMap.put(vertexName, i);
            reverseVertexMap.put(i, vertexName);
        }

        ArrayList<Pair1>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.println("Enter the edges with weights (format: vertex1 vertex2 weight):");
        for (int i = 0; i < E; i++) {
            String src = sc.next();
            String dest = sc.next();
            int wt = sc.nextInt();

            int u = vertexMap.get(src);
            int v = vertexMap.get(dest);

            graph[u].add(new Pair1(wt, v));
            graph[v].add(new Pair1(wt, u)); // Since the graph is undirected
        }

        PrimsSoln.primsAlgo(graph, V, reverseVertexMap);
        sc.close();
    }
}
