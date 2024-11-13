package DSA.DAA.Exam;
import java.util.*;

class QuickSort {
    public void swap(int[] arr, int a, int b) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    public void printArr(int[] A, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    // Partition function with pass tracking and sublist storage
    public int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    // Quick Sort method that displays each pass and stores sublists for each partition
    public void quickSort(int[] arr, int low, int high, int pass, List<int[]> sublists) {
        if (low < high) {
            int pi = partition(arr, low, high);

            // Store a copy of the current sublist generated by partitioning
            int[] sublist = Arrays.copyOfRange(arr, low, high + 1);
            sublists.add(sublist);

            System.out.print("Pass " + pass + ": ");
            printArr(arr, arr.length);

            // Recursive calls for left and right partitions with pass increment
            quickSort(arr, low, pi - 1, pass + 1, sublists);
            quickSort(arr, pi + 1, high, pass + 1, sublists);
        }
    }
}

class OptimalMergePattern {
    public int mergeSublists(List<int[]> sublists) {
        // PriorityQueue to store sublist sums (total value of each sublist)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add the sum of elements in each sublist to the heap and print the sum of each sublist
        System.out.println("\nSum of each sublist:");
        for (int[] sublist : sublists) {
            int sum = Arrays.stream(sublist).sum();
            System.out.println("Sublist: " + Arrays.toString(sublist) + " | Sum: " + sum);
            minHeap.add(sum);
        }

        int totalMergeCost = 0;
        while (minHeap.size() > 1) {
            // Remove the two smallest sums from the heap (representing the sublists being merged)
            int first = minHeap.poll();
            int second = minHeap.poll();

            // The merge cost is the sum of the values of the two sublists
            int mergeCost = first + second;

            // Print the merge details
            System.out.println("Merging sublists with sum " + first + " and " + second + " with cost: " + mergeCost);

            // Add the merged sublist sum back into the heap
            totalMergeCost += mergeCost;
            minHeap.add(mergeCost);
        }
        return totalMergeCost;
    }
}

public class quickomp {
    public static void main(String[] args) {
        QuickSort quickSortObj = new QuickSort();
        OptimalMergePattern optimalMergeObj = new OptimalMergePattern();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        Random rand = new Random();

        // Randomly generate numbers for the array
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(100);
        }

        System.out.println("\nUnsorted Array: ");
        quickSortObj.printArr(arr, n);

        // List to store sublists generated after each Quick Sort pass
        List<int[]> sublists = new ArrayList<>();

        // Perform Quick Sort with pass display and sublist storage
        long startTime = System.nanoTime();
        quickSortObj.quickSort(arr, 0, n - 1, 1, sublists);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        System.out.println("\nSorted Array in Ascending Order:");
        quickSortObj.printArr(arr, n);

        // Display all sublists generated by Quick Sort passes
        System.out.println("\nSublists generated by Quick Sort passes:");
        for (int i = 0; i < sublists.size(); i++) {
            System.out.print("Sublist " + (i + 1) + ": ");
            quickSortObj.printArr(sublists.get(i), sublists.get(i).length);
        }

        System.out.println("\nApplying Optimal Merge Pattern to the generated sublists:");
        int mergeCost = optimalMergeObj.mergeSublists(sublists);
        System.out.println("The minimum total cost of merging all sublists is: " + mergeCost);

        System.out.println("\nTime Taken for Quick Sort: " + executionTime + " nanoseconds");
    }
}