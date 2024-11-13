package DSA.DAA.Exam;

import java.util.*;

public class knapsackcombined {
    // Helper function for Knapsack DP
    static int knapsackUtil(int[] wt, int[] val, int ind, int W, int[][] dp, List<Integer> items) {
        if (ind == 0) {
            if (wt[0] <= W) {
                items.add(0);
                return val[0];
            } else {
                return 0;
            }
        }
        if (dp[ind][W] != -1) {
            return dp[ind][W];
        }

        List<Integer> notTakenItems = new ArrayList<>(items);
        int notTaken = knapsackUtil(wt, val, ind - 1, W, dp, notTakenItems);

        int taken = Integer.MIN_VALUE;
        List<Integer> takenItems = new ArrayList<>(items);

        if (wt[ind] <= W) {
            takenItems.add(ind);
            taken = val[ind] + knapsackUtil(wt, val, ind - 1, W - wt[ind], dp, takenItems);
        }

        if (taken > notTaken) {
            items.clear();
            items.addAll(takenItems);
            dp[ind][W] = taken;
        } else {
            items.clear();
            items.addAll(notTakenItems);
            dp[ind][W] = notTaken;
        }

        return dp[ind][W];
    }

    static int knapsackDP(int[] wt, int[] val, int n, int W) {
        int[][] dp = new int[n][W + 1];
        List<Integer> items = new ArrayList<>();

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int maxVal = knapsackUtil(wt, val, n - 1, W, dp, items);

        System.out.print("DP Items included: ");
        for (int i : items) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();

        return maxVal;
    }

    // Item class for Greedy Approach
    static class Item {
        double weight, profit, ratio;
        int index;

        Item(int index, double weight, double profit) {
            this.index = index;
            this.weight = weight;
            this.profit = profit;
            this.ratio = profit / weight;
        }
    }

    static double knapsackGreedy(Item[] items, double capacity, int n) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double maxProfit = 0.0;
        double remainingCapacity = capacity;

        System.out.println("Greedy Items included: ");
        for (int i = 0; i < n; i++) {
            if (items[i].weight <= remainingCapacity) {
                remainingCapacity -= items[i].weight;
                maxProfit += items[i].profit;
                System.out.println("Item " + (items[i].index + 1) + " (Weight: " + items[i].weight + ", Profit: " + items[i].profit + ")");
            } else {
                double fraction = remainingCapacity / items[i].weight;
                maxProfit += items[i].profit * fraction;
                System.out.println("Item " + (items[i].index + 1) + " (Weight: " + remainingCapacity + ", Profit: " + (items[i].profit * fraction) + ")");
                break;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int[] wt = new int[n];
        int[] val = new int[n];
        Item[] items = new Item[n];

        System.out.println("Enter the weights and profits of the items:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " weight: ");
            wt[i] = scanner.nextInt();
            System.out.print("Item " + (i + 1) + " profit: ");
            val[i] = scanner.nextInt();
            items[i] = new Item(i, wt[i], val[i]);
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        // Knapsack using DP
        System.out.println("\nUsing Dynamic Programming (DP) approach:");
        int maxValueDP = knapsackDP(wt, val, n, capacity);
        System.out.println("Maximum profit (DP): " + maxValueDP);

        // Knapsack using Greedy approach
        System.out.println("\nUsing Greedy approach:");
        long startTime = System.nanoTime();
        double maxValueGreedy = knapsackGreedy(items, capacity, n);
        System.out.println("Maximum profit (Greedy): " + maxValueGreedy);

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Time Taken for Greedy: " + executionTime + " nanoseconds");

        scanner.close();
    }
}
