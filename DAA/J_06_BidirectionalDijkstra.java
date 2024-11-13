package DSA.DAA;
import java.util.*;

class P {
    int node;
    int distance;
    public P(int distance, int node) {
        this.node = node;
        this.distance = distance;
    }
}

class Sol {
    static int bidirectionalDijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S, int T, ArrayList<Integer> visitOrder, int[] parentForward, int[] parentBackward) {
        PriorityQueue<P> pqForward = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        PriorityQueue<P> pqBackward = new PriorityQueue<>((x, y) -> x.distance - y.distance);

        int[] distForward = new int[V];
        int[] distBackward = new int[V];

        Arrays.fill(distForward, Integer.MAX_VALUE);
        Arrays.fill(distBackward, Integer.MAX_VALUE);

        Arrays.fill(parentForward, -1);
        Arrays.fill(parentBackward, -1);

        boolean[] visitedForward = new boolean[V];
        boolean[] visitedBackward = new boolean[V];

        distForward[S] = 0;
        distBackward[T] = 0;

        pqForward.add(new P(0, S));
        pqBackward.add(new P(0, T));

        int best = Integer.MAX_VALUE; // Track the best (shortest) distance found so far

        while (!pqForward.isEmpty() || !pqBackward.isEmpty()) {
            if (!pqForward.isEmpty()) {
                int forwardNode = pqForward.poll().node;
                if (visitedForward[forwardNode]) continue;
                visitedForward[forwardNode] = true;
                visitOrder.add(forwardNode);

                // Relax edges in the forward direction
                for (int i = 0; i < adj.get(forwardNode).size(); i++) {
                    int adjNode = adj.get(forwardNode).get(i).get(0);
                    int edgeWeight = adj.get(forwardNode).get(i).get(1);

                    if (distForward[forwardNode] + edgeWeight < distForward[adjNode]) {
                        distForward[adjNode] = distForward[forwardNode] + edgeWeight;
                        parentForward[adjNode] = forwardNode;
                        pqForward.add(new P(distForward[adjNode], adjNode));
                    }

                    // If the node is visited by the backward search, update the best distance
                    if (visitedBackward[adjNode]) {
                        best = Math.min(best, distForward[adjNode] + distBackward[adjNode]);
                    }
                }
            }

            if (!pqBackward.isEmpty()) {
                int backwardNode = pqBackward.poll().node;
                if (visitedBackward[backwardNode]) continue;
                visitedBackward[backwardNode] = true;

                // Relax edges in the backward direction
                for (int i = 0; i < adj.get(backwardNode).size(); i++) {
                    int adjNode = adj.get(backwardNode).get(i).get(0);
                    int edgeWeight = adj.get(backwardNode).get(i).get(1);

                    if (distBackward[backwardNode] + edgeWeight < distBackward[adjNode]) {
                        distBackward[adjNode] = distBackward[backwardNode] + edgeWeight;
                        parentBackward[adjNode] = backwardNode;
                        pqBackward.add(new P(distBackward[adjNode], adjNode));
                    }

                    // If the node is visited by the forward search, update the best distance
                    if (visitedForward[adjNode]) {
                        best = Math.min(best, distForward[adjNode] + distBackward[adjNode]);
                    }
                }
            }

            // Early termination: If the searches meet, return the shortest path
            if (best != Integer.MAX_VALUE) {
                return best;
            }
        }
        return -1; // If no path exists between S and T
    }

    static void printPath(int node, int[] parent, HashMap<Integer, String> reverseVertexMap) {
        if (node == -1) {
            return;
        }
        printPath(parent[node], parent, reverseVertexMap);
        System.out.print(reverseVertexMap.get(node) + " ");
    }
}

public class J_06_BidirectionalDijkstra {
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

        System.out.print("Enter the destination vertex (T): ");
        String TLabel = sc.nextLine();
        int T = vertexMap.get(TLabel);

        ArrayList<Integer> visitOrder = new ArrayList<>();
        Sol obj = new Sol();
        int[] parentForward = new int[V];
        int[] parentBackward = new int[V];

        int shortestDistance = obj.bidirectionalDijkstra(V, adj, S, T, visitOrder, parentForward, parentBackward);

        if (shortestDistance != -1) {
            System.out.println("Shortest distance from " + SLabel + " to " + TLabel + " is: " + shortestDistance);

            System.out.print("Path from " + SLabel + " to " + TLabel + ": ");
            obj.printPath(T, parentForward, reverseVertexMap);
            obj.printPath(parentBackward[T], parentBackward, reverseVertexMap);
            System.out.println();
        } else {
            System.out.println("No path exists between " + SLabel + " and " + TLabel);
        }

        System.out.println("\nOrder of vertices visited in the forward direction:");
        for (int i = 0; i < visitOrder.size(); i++) {
            System.out.print(reverseVertexMap.get(visitOrder.get(i)) + " ,");
        }
        sc.close();
    }
}
