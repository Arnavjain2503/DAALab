package DSA.DAA;

import java.util.Scanner;
import java.util.Random;

class Searching {
    //Linear Search
    public void LinearFun(int arr[], int n) {
        int flag = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Element to Search: ");
        int s = sc.nextInt();
        for (int i = 0; i < n; i++) {
            if (arr[i] == s) {
                System.out.println("Found at Position: " +
                        (i + 1));
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("Not Found!!");
        }
    }
    public void printarr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    // Binary Search
    public void BinaryFun(int arr[], int n) {
        // Sorting
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("Sorted Array: ");
        printarr(arr);
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Element to Search: ");
        int s = sc.nextInt();
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == s) {
                System.out.println("Found at pos: " + (mid
                        + 1));
                return;
            }
            if (arr[mid] < s) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println("Not Found!!");
    }
    public static void LinearFun1(int arr[], int n) {
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        System.out.println("\nEnter Element to Search: ");
        int s = sc.nextInt();
        for (int i = 0; i < n; i++) {
            if (arr[i] == s) {
                System.out.println("Found at Position: " +
                        (i + 1));
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("Not Found!!");
        }
    }
}

public class search {
    public static void main(String[] args) {
        Searching obj = new Searching();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of element: ");
        int n = sc.nextInt();
        int arr[] = new int[n];

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(1000);
        }
        obj.printarr(arr);

        long startTime = System.nanoTime();
        // obj.LinearFun(arr, n);
        obj.LinearFun1(arr, n);
        long endTime = System.nanoTime();

        long executionTime = (endTime - startTime) / 1000000;
        System.out.println("Time Taken for linear Search: " + executionTime + "ms");


        long startTime1 = System.nanoTime();
        obj.BinaryFun(arr, n);
        long endTime1 = System.nanoTime();
        long executionTime1 = (endTime1 - startTime1) / 1000000;
        System.out.println("Time Taken binary Search: " + executionTime1 + "ms");
    }
}

