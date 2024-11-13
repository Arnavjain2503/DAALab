package DSA.DAA.Exam;

import java.util.Scanner;

class quickbinary {

    // Swap elements in the array for quick sort
    public static void swap(double[] weights, String[] products, int a, int b) {
        double tempWeight = weights[a];
        weights[a] = weights[b];
        weights[b] = tempWeight;

        String tempProduct = products[a];
        products[a] = products[b];
        products[b] = tempProduct;
    }

    // Print the array after each pass
    private static void printPass(double[] weights, String[] products, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(products[i] + ": " + weights[i] + " kg");
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println();
    }

    // Partition method for Quick Sort
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

    // Quick Sort
    public static void quickSort(double[] weights, String[] products, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(weights, products, low, high);
            quickSort(weights, products, low, pivotIndex - 1);
            quickSort(weights, products, pivotIndex + 1, high);
        }
    }

    // Binary Search
    public static int binarySearch(double[] weights, String[] products, double targetWeight) {
        int left = 0;
        int right = weights.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (weights[mid] == targetWeight) {
                return mid; // Return the index of the product with the target weight
            } else if (weights[mid] < targetWeight) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Target weight not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of products: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] products = new String[n];
        double[] weights = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter product name " + (i + 1) + ": ");
            products[i] = scanner.nextLine();
            System.out.print("Enter weight for " + products[i] + ": ");
            weights[i] = scanner.nextDouble();
            scanner.nextLine();
        }

        // Display the unsorted array
        System.out.println("\nUnsorted Product Data:");
        printPass(weights, products, n);

        // Perform Quick Sort and display passes
        System.out.println("\nQuick Sort passes:");
        quickSort(weights, products, 0, n - 1);

        System.out.println("\nSorted Product Data (by weight):");
        printPass(weights, products, n);

        // Perform Binary Search
        System.out.print("\nEnter the weight to search for: ");
        double targetWeight = scanner.nextDouble();
        int searchResult = binarySearch(weights, products, targetWeight);

        if (searchResult != -1) {
            System.out.println("Product found - Name: " + products[searchResult] + ", Weight: " + weights[searchResult] + " kg");
        } else {
            System.out.println("Product with weight " + targetWeight + " kg not found.");
        }

        scanner.close();
    }
}
