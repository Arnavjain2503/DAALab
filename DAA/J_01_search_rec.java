package DSA.DAA;

import java.util.Scanner;
class searching {
    public int binarySearch(int[] array, int x, int low, int high) {
        if (high >= low) {
            int mid = (low + high) / 2;
            if (array[mid] == x) {
                return mid;
            } else if (array[mid] > x) {
                return binarySearch(array, x, low, mid - 1);
            } else {
                return binarySearch(array, x, mid + 1, high);
            }
        }
        return -1;
    }
    public int linearSearchRec(int n, int[] marks, int a) {
        if (n == 0) {
            return 0;
        } else {
            int count = linearSearchRec(n - 1, marks, a);
            if (marks[n - 1] == a) {
                System.out.println(a + " found at Index " + (n - 1));
                count++;
            }
            return count;
        }
    }
}

public class J_01_search_rec{
    public static void main(String[] args) {
        searching obj = new searching();
        Scanner scanner = new Scanner(System.in);
        // Binary Search Section
        System.out.println("Binary Search:");

        System.out.print("Enter size of the array: ");
        int size = scanner.nextInt();
        int[] data = new int[size];

        System.out.println("NOTE: ENTER ELEMENTS IN SORTED ORDER");
        for (int i = 0; i < size; i++) {
            System.out.print("Enter Element " + i + ": ");
            data[i] = scanner.nextInt();
        }

        System.out.print("Enter Element to Search: ");
        int x = scanner.nextInt();

        int result = obj.binarySearch(data, x, 0, size - 1);
        if (result == -1) {
            System.out.println("Not found");
        }
        else {
            System.out.println("Element is found at index " + result);
        }

        // Linear Search Section
        System.out.println("\nLinear Search:");

        System.out.print("Enter size of the array: ");
        int n = scanner.nextInt();

        int[] data1 = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter " + i + " element: ");
            data1[i] = scanner.nextInt();
        }
        System.out.print("Enter Element to search: ");
        int a = scanner.nextInt();

        int res = obj.linearSearchRec(n, data1, a);
        if (res == 0) {
            System.out.println("Not Found in Array!");
        }
        else {
            System.out.println(a + " occurs " + res + " times in the array.");
        }
        scanner.close();
    }
}