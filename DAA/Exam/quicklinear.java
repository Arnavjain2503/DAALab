package DSA.DAA.Exam;

import java.util.Scanner;

class InventoryManagement {

    // Method to swap elements in the array
    public static void swap(double[] weights, String[] products, int a, int b) {
        double tempWeight = weights[a];
        weights[a] = weights[b];
        weights[b] = tempWeight;

        String tempProduct = products[a];
        products[a] = products[b];
        products[b] = tempProduct;
    }

    // Method to print the array after each pass
    private static void printPass(double[] weights, String[] products, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(products[i] + ": " + weights[i] + " kg");
            if (i < size - 1){
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    // Partition method for Quick Sort, with low and high as array bounds
    public static int partition(double[] weights, String[] products, int low, int high) {
        double pivot = weights[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (weights[j] <= pivot) {
                i++;
                swap(weights, products, i, j);
            }
        }
        swap(weights, products, i + 1, high);

        printPass(weights, products, high + 1);

        return i + 1;
    }

    // Quick Sort method
    public static void quickSort(double[] weights, String[] products, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(weights, products, low, high);
            quickSort(weights, products, low, pivotIndex - 1);
            quickSort(weights, products, pivotIndex + 1, high);
        }
    }

    // Linear Search method optimized for searching by weight
    public static int linearSearch(double[] weights, String[] products, double targetWeight) {
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] == targetWeight) {
                return i; // Return the index of the product with the target weight
            }
        }
        return -1; // Target weight not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of products
        System.out.print("Enter the number of products: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Arrays to store product names and weights
        String[] products = new String[n];
        double[] weights = new double[n];

        // Input product names and weights
        for (int i = 0; i < n; i++) {
            System.out.print("Enter product name " + (i + 1) + ": ");
            products[i] = scanner.nextLine();
            System.out.print("Enter weight for " + products[i] + ": ");
            weights[i] = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character
        }

        // Perform Linear Search
        System.out.print("\nEnter the weight to search for: ");
        double targetWeight = scanner.nextDouble();

        int searchResult = linearSearch(weights, products, targetWeight);
        if (searchResult != -1) {
            System.out.println("Product found - Name: " + products[searchResult] + ", Weight: " + weights[searchResult] + " kg");
        } else {
            System.out.println("Product with weight " + targetWeight + " kg not found.");
        }

        // Display the unsorted array   
        System.out.println("\nUnsorted Product Data:");
        printPass(weights, products, n);

        // Perform Quick Sort and display passes
        System.out.println("\nSorting passes:");
        long startTime = System.nanoTime();
        quickSort(weights, products, 0, n - 1);
        long endTime = System.nanoTime();

        // Display the sorted array
        System.out.println("\nSorted Product Data (by weight):");
        printPass(weights, products, n);

        long executionTime = (endTime - startTime);
        System.out.println("\nTime taken for Quick Sort: " + executionTime + " nanoseconds");

        scanner.close();
    }
}
