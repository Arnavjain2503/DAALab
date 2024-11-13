package DSA.DAA;
import java.util.Scanner;

public class J_08_floyd_warshall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take input for the number of vertices
        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();

        // Initialize the adjacency matrix
        int[][] matrix = new int[V][V];
        System.out.println("Enter the adjacency matrix (-1 for no edge):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matrix[i][j] = sc.nextInt();
                if (matrix[i][j] == -1 && i != j) {
                    matrix[i][j] = (int)(1e9);
                }
            }
        }

        // Floyd-Warshall algorithm to find the shortest paths
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        // Check for negative cycle and print shortest path matrix
        boolean hasNegativeCycle = false;
        for (int i = 0; i < V; i++) {
            if (matrix[i][i] < 0) {
                hasNegativeCycle = true;
                break;
            }
        }
        if (hasNegativeCycle) {
            System.out.println("Negative cycle exists!");
        }
        else {
            System.out.println("No negative cycle detected.\n");
            System.out.println("The shortest distances between every pair of vertices:");
            // Print column headers
            System.out.print("    ");
            for (int i = 0; i < V; i++) {
                System.out.printf("%4d", i);
            }
            System.out.println();

            // Print row headers and shortest path matrix
            for (int i = 0; i < V; i++) {
                System.out.printf("%4d", i);
                for (int j = 0; j < V; j++) {
                    if (matrix[i][j] == (int)(1e9)) {
                        System.out.printf("%4s", "INF");
                    } else {
                        System.out.printf("%4d", matrix[i][j]);
                    }
                }
                System.out.println();
            }
        }
        sc.close();
    }
}
