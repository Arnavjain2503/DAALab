package DSA.DAA;

import java.util.*;

class merge_sort {
   public void printArr(int[] A, int size) {
       for (int i = 0; i < size; i++) {
           System.out.print(A[i] + ", ");
       }
       System.out.println();
   }

   public void Merge(int arr[], int low, int mid, int high) {
       //number of elements between the low and high indices in the original array.
       int merged[] = new int[high - low + 1];  // Final array
       // Keeping track of index of arr1
       int left = low;
       // Keeping track of index of arr2
       int right = mid + 1;
       int x = 0;
       // Comparing elements of both array and adding in final array O(n)
       while (left <= mid && right <= high) {
           if (arr[left] <= arr[right]) {
               merged[x++] = arr[left++];
           }
           else {
               merged[x++] = arr[right++];
           }
       }
       // When 1 element is left in arr1
       while (left <= mid) {
           merged[x++] = arr[left++];
       }
       // When 1 element is left in arr2
       while (right <= high) {
           merged[x++] = arr[right++];
       }
       for (int i = low; i <= high; i++) {
           arr[i] = merged[i - low];
       }
//       System.out.println("Conquer: ");
//       printArr(arr, arr.length);
   }

   public void mergeSort(int arr[], int low, int high) {
       if (low >= high) {
           return;
       }
       int mid = low + (high - low) / 2;
       // O(logn)
       mergeSort(arr, low, mid);
       mergeSort(arr, mid + 1, high);
       Merge(arr, low, mid, high);
//       System.out.println("Divide: ");
//       printArr(arr, arr.length);
   }

   public int removeDuplicates(int arr[], int n) {
       if (n == 0 || n == 1) {
           return n;}
       int[] temp = new int[n];
       int j = 0;
       for (int i = 0; i < n - 1; i++) {
           if (arr[i] != arr[i + 1]) {
               temp[j] = arr[i];
               j++;
           }
       }
       temp[j++] = arr[n - 1];
       for (int i = 0; i < j; i++) {
           arr[i] = temp[i];
       }
       return j;
   }
}

public class J_03_Merge_Sort {
   public static void main(String[] args) {
       merge_sort obj = new merge_sort();
       Scanner sc = new Scanner(System.in);

       System.out.print("Enter the number of elements: ");
       int n = sc.nextInt();
       Random rand = new Random();
       int[] arr = new int[n];
       for (int i = 0; i < n; i++) {
           arr[i] = rand.nextInt(100);
       }

       System.out.println("\nUnsorted Array: ");
       obj.printArr(arr, n);
       System.out.println("");

       long startTime = System.nanoTime();
       obj.mergeSort(arr, 0, n - 1);
       long endTime = System.nanoTime();

       long executionTime = (endTime - startTime);
       System.out.println("Sorted Array in Ascending Order:");
       obj.printArr(arr, n);
       System.out.println("");

       System.out.println("Array after removing duplicates: ");
       int newlen = obj.removeDuplicates(arr, n);
       obj.printArr(arr, newlen);
       System.out.println("");

       System.out.println("Time Taken for Merge Sort: " + executionTime + " nanoseconds");
   }
}