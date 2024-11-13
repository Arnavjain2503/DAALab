package DSA.DAA;
import java.util.*;

public class J_07_knapsack_greedy {
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

    public static double knapsack(Item[] items, double capacity, int n) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double maxProfit = 0.0;
        double remainingCapacity = capacity;

        System.out.println("Items included in the knapsack:");
        for (int i = 0; i < n; i++) {
            if (items[i].weight <= remainingCapacity) {
                remainingCapacity -= items[i].weight;
                maxProfit += items[i].profit;
                System.out.println("Item " + (items[i].index + 1) + " (Weight: " + items[i].weight + ", Profit: " + items[i].profit + ")");
            }
            else {
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

        Item[] items = new Item[n];

        System.out.println("Enter the weights and profits of the items:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " weight: ");
            double weight = scanner.nextDouble();
            System.out.print("Item " + (i + 1) + " profit: ");
            double profit = scanner.nextDouble();
            items[i] = new Item(i, weight, profit);
        }

        System.out.print("Enter the capacity of the knapsack: ");
        double capacity = scanner.nextDouble();

        long startTime = System.nanoTime();

        double maxValue = knapsack(items, capacity, n);
        System.out.println("Maximum profit that can be obtained: " + maxValue);

        long endTime = System.nanoTime();

        long executionTime = (endTime - startTime);
        System.out.println("\nTime Taken: " + executionTime + " nanoseconds");
        scanner.close();
    }
}

//public class knapsack_q {
//    static class Item {
//        double weight, profit, ratio;
//        int index;
//        Item(int index, double weight, double profit) {
//            this.index = index;
//            this.weight = weight;
//            this.profit = profit;
//            this.ratio = profit / weight;
//        }
//    }
//    public static void bubbleSort(Item[] items, int n) {
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = 0; j < n - i - 1; j++) {
//                if (items[j].ratio < items[j + 1].ratio) {
//                    Item temp = items[j];
//                    items[j] = items[j + 1];
//                    items[j + 1] = temp;
//                }
//            }
//        }
//    }
//    public static double knapsack(Item[] items, double capacity, int n) {
//        bubbleSort(items, n);
//
//        double maxProfit = 0.0;
//        double remainingCapacity = capacity;
//
//        System.out.println("Items included in the knapsack:");
//
//        for (int i = 0; i < n; i++) {
//            if (items[i].weight <= remainingCapacity) {
//                remainingCapacity -= items[i].weight;
//                maxProfit += items[i].profit;
//                System.out.println("Item " + (items[i].index + 1) + " (Weight: " + items[i].weight + ", Profit: " + items[i].profit + ")");
//            }
//            else {
//                double fraction = remainingCapacity / items[i].weight;
//                maxProfit += items[i].profit * fraction;
//                System.out.println("Item " + (items[i].index + 1) + " (Weight: " + remainingCapacity + ", Profit: " + (items[i].profit * fraction) + ")");
//                break;
//            }
//        }
//        return maxProfit;
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter the number of items: ");
//        int n = scanner.nextInt();
//
//        Item[] items = new Item[n];
//
//        System.out.println("Enter the weights and profits of the items:");
//        for (int i = 0; i < n; i++) {
//            System.out.print("Item " + (i + 1) + " weight: ");
//            double weight = scanner.nextDouble();
//            System.out.print("Item " + (i + 1) + " profit: ");
//            double profit = scanner.nextDouble();
//            items[i] = new Item(i, weight, profit);
//        }
//
//        System.out.print("Enter the capacity of the knapsack: ");
//        double capacity = scanner.nextDouble();
//
//        long startTime = System.nanoTime();
//
//        double maxValue = knapsack(items, capacity, n);
//        System.out.println("Maximum profit that can be obtained: " + maxValue);
//
//        long endTime = System.nanoTime();
//
//        long executionTime = (endTime - startTime);
//        System.out.println("\nTime Taken: " + executionTime + " nanoseconds");
//
//        scanner.close();
//    }
//}