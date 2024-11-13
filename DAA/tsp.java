package DSA.DAA;
import java.util.*;

public class tsp {
    // Number of vertices in the graph
    private static int N;

    // A large value representing infinity
    private static final int INF = Integer.MAX_VALUE;

    // The graph as an adjacency list
    private static Map<Integer, List<int[]>> graph;

    // Method to solve the TSP problem
    public static int tsp(int mask, int pos, int[][] dp) {
        // If all cities have been visited, return cost to go back to start
        if (mask == (1 << N) - 1) {
            // Find if there is a direct connection from pos to start (0)
            for (int[] edge : graph.get(pos)) {
                if (edge[0] == 0) {
                    return edge[1];  // return the weight of the edge
                }
            }
            return INF;  // No direct connection back to start
        }

        // If the solution is already computed
        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int minCost = INF;

        // Try to go to every other city
        for (int[] edge : graph.get(pos)) {
            int city = edge[0]; // neighboring city
            int cost = edge[1]; // weight of the edge

            // Check if the city is already visited
            if ((mask & (1 << city)) == 0) {
                int newCost = tsp(mask | (1 << city), city, dp);
                if (newCost < INF) {
                    minCost = Math.min(minCost, cost + newCost);
                }
            }
        }

        // Save the result in the dp table
        dp[mask][pos] = minCost;
        return minCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of nodes:");
        N = scanner.nextInt();

        // Initialize the adjacency list
        graph = new HashMap<>();

        for (int i = 0; i < N; i++) {
            graph.put(i, new ArrayList<>());
        }

        System.out.println("Enter the edges in the format 'from to weight' (type '-1' to stop):");
        while (true) {
            int from = scanner.nextInt();
            if (from == -1) break;  // End input with -1
            int to = scanner.nextInt();
            int weight = scanner.nextInt();

            // Add the edge to the graph
            graph.get(from).add(new int[]{to, weight});
        }

        // DP table
        int[][] dp = new int[1 << N][N];

        // Initialize DP table with -1
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Starting the tour from city A (0th index)
        int minCost = tsp(1, 0, dp);

        // Print the minimum cost of the TSP
        if (minCost >= INF) {
            System.out.println("No valid TSP tour found.");
        } else {
            System.out.println("The minimum cost of the TSP is: " + minCost);
        }
    }
}

/*
0 1 4
0 3 5
1 2 1
1 4 6
2 0 2
2 3 3
3 2 1
3 4 2
4 0 1
4 3 4
-1
 */