package DSA.DAA.Exam;

import java.util.*;

class merge_sorttt {
    // Method to print cities and their temperatures
    public void printArr(double[] A, String[] cities, int size) {
        if (size == 0) {
            System.out.println("No cities to display.");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(cities[i] + ": " + A[i] + "°C, ");
        }
        System.out.println();
    }

    // Merge function for sorting the cities and temperatures
    public void Merge(double arr[], String[] cities, int low, int mid, int high) {
        double merged[] = new double[high - low + 1];
        String[] mergedCities = new String[high - low + 1];
        int left = low, right = mid + 1, x = 0;

        // Merging the two sorted halves
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                merged[x] = arr[left];
                mergedCities[x] = cities[left];
                left++;
            } else {
                merged[x] = arr[right];
                mergedCities[x] = cities[right];
                right++;
            }
            x++;
        }

        while (left <= mid) {
            merged[x] = arr[left];
            mergedCities[x] = cities[left];
            left++;
            x++;
        }

        while (right <= high) {
            merged[x] = arr[right];
            mergedCities[x] = cities[right];
            right++;
            x++;
        }

        for (int i = low; i <= high; i++) {
            arr[i] = merged[i - low];
            cities[i] = mergedCities[i - low];
        }

        // Print the array after merging
        System.out.print("Merged: ");
        printArr(arr, cities, arr.length);
    }

    // Merge Sort method
    public void mergeSort(double arr[], String[] cities, int low, int high) {
        if (low >= high) return;

        // Print the current subarray before splitting
        System.out.print("\nDividing: ");
        printArr(arr, cities, arr.length);

        int mid = low + (high - low) / 2;

        // Recursively split the array
        mergeSort(arr, cities, low, mid);
        mergeSort(arr, cities, mid + 1, high);

        // Merge the subarrays and print the merge pass
        Merge(arr, cities, low, mid, high);
    }

}

class search1 {
    // Binary Search method to find a city by name
    public int binarySearch(String[] cities, double[] temperatures, String city, int low, int high) {
        if (high >= low) {
            int mid = (low + high) / 2;
            if (cities[mid].equalsIgnoreCase(city)) {
                return mid;
            } else if (cities[mid].compareToIgnoreCase(city) > 0) {
                return binarySearch(cities, temperatures, city, low, mid - 1);
            } else {
                return binarySearch(cities, temperatures, city, mid + 1, high);
            }
        }
        return -1;
    }
}

public class mergebinary {
    public static void main(String[] args) {
        merge_sorttt obj = new merge_sorttt();
        search1 searchObj = new search1();
        Scanner sc = new Scanner(System.in);

        // Input the number of cities
        System.out.print("Enter the number of cities: ");
        int n = sc.nextInt();
        sc.nextLine();  // Consume newline character

        // Arrays to store city names and temperatures
        String[] cities = new String[n];
        double[] temperatures = new double[n];

        // Input the cities and their respective temperatures
        for (int i = 0; i < n; i++) {
            System.out.print("Enter city name " + (i + 1) + ": ");
            cities[i] = sc.nextLine();
            System.out.print("Enter temperature for " + cities[i] + ": ");
            temperatures[i] = sc.nextDouble();
            sc.nextLine();
        }

        // Perform Binary Search by City Name
        System.out.print("\nEnter the city name to search for: ");
        String targetCity = sc.nextLine();

        int result = searchObj.binarySearch(cities, temperatures, targetCity, 0, n - 1);
        if (result != -1) {
            System.out.println("City: " + cities[result] + ", Temperature: " + temperatures[result] + "°C");
        } else {
            System.out.println("City " + targetCity + " not found.");
        }

        // Display the unsorted array of cities and temperatures
        System.out.println("\nUnsorted Data:");
        obj.printArr(temperatures, cities, n);

        // Sorting the data using Merge Sort
        long startTime = System.nanoTime();
        obj.mergeSort(temperatures, cities, 0, n - 1);
        long endTime = System.nanoTime();

        // Display the sorted array
        long executionTime = (endTime - startTime);
        System.out.println("\nSorted Data (by temperature):");
        obj.printArr(temperatures, cities, n);
        System.out.println("\nTime taken for Merge Sort: " + executionTime + " nanoseconds");

        sc.close();
    }
}
