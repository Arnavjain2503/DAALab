package DSA.DAA.Exam;
import java.util.*;

class Pairr {
    int node;
    int distance;
    public Pairr(int distance, int node) {
        this.node = node;
        this.distance = distance;
    }
}

class Solution {

    // Function to find the shortest distance of all the vertices from the source vertex S using Dijkstra's algorithm.
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S, ArrayList<Integer> visitOrder, int[] parent) {
        PriorityQueue<Pairr> pq = new PriorityQueue<Pairr>((x, y) -> x.distance - y.distance);
        boolean[] sptSet = new boolean[V];
        int[] key = new int[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);
        Arrays.fill(parent, -1);

        key[S] = 0;
        pq.add(new Pairr(0, S));

        while (!pq.isEmpty()) {
            int dis = pq.peek().distance;
            int node = pq.peek().node;
            pq.poll();

            if (sptSet[node]) continue;

            sptSet[node] = true;
            visitOrder.add(node);

            for (int i = 0; i < adj.get(node).size(); i++) {
                int adjNode = adj.get(node).get(i).get(0);
                int edgeWeight = adj.get(node).get(i).get(1);

                if (!sptSet[adjNode] && dis + edgeWeight < key[adjNode]) {
                    key[adjNode] = dis + edgeWeight;
                    parent[adjNode] = node;
                    pq.add(new Pairr(key[adjNode], adjNode));
                }
            }
        }
        return key;
    }

    // Function to print the path from source to destination node
    static void printPath(int node, int[] parent) {
        if (node == -1) {
            return;
        }
        printPath(parent[node], parent);
        System.out.print(node + " ");
    }

    // Floyd-Warshall algorithm for finding shortest distances between all pairs of vertices
    static void floydWarshall(int V, int[][] matrix) {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
    }

    // Function to print the shortest path matrix
    static void printFloydWarshallResult(int V, int[][] matrix) {
        System.out.println("The shortest distances between every pair of vertices:");
        System.out.print("    ");
        for (int i = 0; i < V; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();

        for (int i = 0; i < V; i++) {
            System.out.printf("%4d", i);
            for (int j = 0; j < V; j++) {
                if (matrix[i][j] == (int) 1e9) {
                    System.out.printf("%4s", "INF");
                } else {
                    System.out.printf("%4d", matrix[i][j]);
                }
            }
            System.out.println();
        }
    }
}

public class dijkstrafloyd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking input for the number of vertices and edges
        System.out.print("Enter the number of vertices (V): ");
        int V = sc.nextInt();

        System.out.print("Enter the number of edges (E): ");
        int E = sc.nextInt();

        sc.nextLine(); // Consume newline

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

        // Apply Dijkstra's algorithm
        ArrayList<Integer> visitOrder = new ArrayList<>();
        Solution obj = new Solution();
        int[] parent = new int[V];
        int[] dijkstraResult = obj.dijkstra(V, adj, S, visitOrder, parent);

        System.out.println("Dijkstra's Shortest distances from source vertex " + SLabel + ":");
        for (int i = 0; i < V; i++) {
            String vertex = reverseVertexMap.get(i);
            if (i == S) {
                System.out.println(SLabel + " to " + vertex + " is " + dijkstraResult[i] + " (it's the source)");
            } else {
                System.out.print(SLabel + " to " + vertex + " is " + dijkstraResult[i] + ". Path: ");
                obj.printPath(i, parent);
                System.out.println();
            }
        }

        // Now, perform Floyd-Warshall algorithm
        int[][] matrix = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matrix[i][j] = (i == j) ? 0 : (int) 1e9;
            }
        }

        // Fill the matrix with edge weights
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                int v = adj.get(i).get(j).get(0);
                int w = adj.get(i).get(j).get(1);
                matrix[i][v] = w;
            }
        }

        // Apply Floyd-Warshall algorithm
        obj.floydWarshall(V, matrix);

        // Print the result of Floyd-Warshall algorithm
        obj.printFloydWarshallResult(V, matrix);

        sc.close();
    }
}
