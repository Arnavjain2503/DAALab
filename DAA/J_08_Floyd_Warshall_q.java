package DSA.DAA;
import java.util.Scanner;

class question {
    public void shortest_distance(int[][] matrix) {
        int n = matrix.length;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if(matrix[i][i] < 0){
                System.out.println("Negative cycle exist!");
            }
        }

    }

    // Function to convert total minutes to "hour:minute" format
    public String convertToTimeFormat(int minutes) {
        if (minutes == (int)(1e9)) {
            return "INF";
        }
        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;
        return String.format("%02d:%02d", hours, remainingMinutes);
    }
}

public class J_08_Floyd_Warshall_q {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take input for the number of vertices
        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();

        // Initialize the adjacency matrix
        int[][] matrix = new int[V][V];

        System.out.println("Enter the adjacency matrix (hour:minute format, INF for no edge):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                String time = sc.next();
                if (time.equals("INF")) {
                    matrix[i][j] = (int)(1e9); // Representing infinity with a large number
                }
                else {
                    String[] timeparts = time.split(":");
                    int hours = Integer.parseInt(timeparts[0]);
                    int minutes = Integer.parseInt(timeparts[1]);
                    matrix[i][j] = hours * 60 + minutes; // Convert to total minutes
                }
            }
        }

        // Call the Floyd-Warshall function
        question obj = new question();
        obj.shortest_distance(matrix);

        // Print the result matrix with descriptive output
        System.out.println("\nThe shortest distances between every pair of vertices (in hour:minute format):");

        // Print column headers
        System.out.print("    ");
        for (int i = 0; i < V; i++) {
            System.out.printf("%8d", i);
        }
        System.out.println();

        // Print row headers and matrix
        for (int i = 0; i < V; i++) {
            System.out.printf("%4d", i); // Row header
            for (int j = 0; j < V; j++) {
                System.out.printf("%8s", obj.convertToTimeFormat(matrix[i][j]));
            }
            System.out.println();
        }

        sc.close();
    }
}
