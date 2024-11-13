package DSA.DAA;
import java.util.*;

public class J_07_knapsack_DP {
    // Helper function to solve the knapsack problem recursively
    static int knapsackUtil(int[] wt, int[] val, int ind, int W, int[][] dp, List<Integer> items) {
        // Base case: If there are no items or the knapsack capacity is zero
        if (ind == 0) {
            if (wt[0] <= W) {
                items.add(0); // Include the first item
                return val[0];
            }
            else {
                return 0;
            }
        }
        // If the result for this subproblem is already calculated, return it
        if (dp[ind][W] != -1) {
            return dp[ind][W];
        }

        // Calculate the maximum value when the current item is not taken
        List<Integer> notTakenItems = new ArrayList<>(items);
        int notTaken = knapsackUtil(wt, val, ind - 1, W, dp, notTakenItems);

        // Calculate the maximum value when the current item is taken
        int taken = Integer.MIN_VALUE;
        List<Integer> takenItems = new ArrayList<>(items);

        if (wt[ind] <= W) {
            takenItems.add(ind); // Include the current item
            taken = val[ind] + knapsackUtil(wt, val, ind - 1, W - wt[ind], dp, takenItems);
        }

        // Store and return the result for the current state
        if (taken > notTaken) {
            items.clear();
            items.addAll(takenItems);
            dp[ind][W] = taken;
        }
        else {
            items.clear();
            items.addAll(notTakenItems);
            dp[ind][W] = notTaken;
        }

        return dp[ind][W];
    }
    // Function to solve the 0/1 Knapsack problem using dynamic programming
    static int knapsack(int[] wt, int[] val, int n, int W) {
        // Create a 2D DP array to store the maximum value for each subproblem
        int dp[][] = new int[n][W + 1];
        List<Integer> items = new ArrayList<>();

        // Initialize the DP array with -1 to indicate that subproblems are not solved
        for (int row[] : dp) {
            Arrays.fill(row, -1);
        }

        // Call the recursive knapsackUtil function to solve the problem
        int maxVal = knapsackUtil(wt, val, n - 1, W, dp, items);

        // Print the selected items
        System.out.print("Items included in the knapsack: ");
        for (int i : items) {
            System.out.print(i+1 + " ");
        }
        System.out.println();

//        System.out.println("DP Table:");
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j <= W; j++) {
//                System.out.print(dp[i][j] + "\t");
//            }
//            System.out.println();
//        }

        return maxVal;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();

        int[] wt = new int[n];
        int[] val = new int[n];

        System.out.println("Enter the weights of the items: ");
        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }

        System.out.println("Enter the values of the items: ");
        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int W = sc.nextInt();

        System.out.println("The Maximum value of items hiker can pack without exceeding the bagback's weight limit is :" + knapsack(wt, val, n, W));

        sc.close();
    }
}