package DSA.DAA.Exam;
import java.util.*;

class mergelinear {

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

    // Merge method for merge sort
    public static void merge(double[] weights, String[] products, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        double[] leftWeights = new double[n1];
        double[] rightWeights = new double[n2];
        String[] leftProducts = new String[n1];
        String[] rightProducts = new String[n2];

        for (int i = 0; i < n1; i++) {
            leftWeights[i] = weights[left + i];
            leftProducts[i] = products[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightWeights[j] = weights[mid + 1 + j];
            rightProducts[j] = products[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftWeights[i] <= rightWeights[j]) {
                weights[k] = leftWeights[i];
                products[k] = leftProducts[i];
                i++;
            } else {
                weights[k] = rightWeights[j];
                products[k] = rightProducts[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            weights[k] = leftWeights[i];
            products[k] = leftProducts[i];
            i++;
            k++;
        }

        while (j < n2) {
            weights[k] = rightWeights[j];
            products[k] = rightProducts[j];
            j++;
            k++;
        }

        printPass(weights, products, right + 1);
    }

    // Merge Sort
    public static void mergeSort(double[] weights, String[] products, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(weights, products, left, mid);
            mergeSort(weights, products, mid + 1, right);
            merge(weights, products, left, mid, right);
        }
    }

    // Linear Search
    public static int linearSearch(double[] weights, String[] products, double targetWeight) {
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] == targetWeight) {
                return i;
            }
        }
        return -1;
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

        System.out.print("\nEnter the weight to search for: ");
        double targetWeight = scanner.nextDouble();
        int searchResult = linearSearch(weights, products, targetWeight);
        if (searchResult != -1) {
            System.out.println("Product found - Name: " + products[searchResult] + ", Weight: " + weights[searchResult] + " kg");
        } else {
            System.out.println("Product with weight " + targetWeight + " kg not found.");
        }

        System.out.println("\nUnsorted Product Data:");
        printPass(weights, products, n);

        System.out.println("\nQuick Sort passes:");
        quickSort(weights, products, 0, n - 1);
        System.out.println("\nSorted Product Data (by weight with Quick Sort):");
        printPass(weights, products, n);

        // Reset the array and sort again using merge sort
        System.out.println("\nResetting data for merge sort...");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight for " + products[i] + ": ");
            weights[i] = scanner.nextDouble();
        }

        System.out.println("\nMerge Sort passes:");
        mergeSort(weights, products, 0, n - 1);
        System.out.println("\nSorted Product Data (by weight with Merge Sort):");
        printPass(weights, products, n);

        scanner.close();
    }
}
