package DSA.DAA;

import java.util.*;

class quick_sort {
   public void swap(int[] arr, int a, int b) {
       int t = arr[a];
       arr[a] = arr[b];
       arr[b] = t;
   }
   public void printArr(int[] A, int size) {
       for (int i = 0; i < size; i++) {
           System.out.print(A[i] + ", ");
       }
       System.out.println();
   }

   // Pivot=last element
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

   public void quickSort(int[] arr, int low, int high) {
       if (low < high) {
           int pi = partition(arr, low, high);
           quickSort(arr, low, pi - 1);
           quickSort(arr, pi + 1, high);
       }
   }

   public int removeDuplicates(int arr[], int n)
   {
       if (n == 0 || n == 1)
           return n;

       int[] temp = new int[n];

       int j = 0;
       for (int i = 0; i < n - 1; i++)
           if (arr[i] != arr[i + 1])
               temp[j++] = arr[i];

       temp[j++] = arr[n - 1];

       for (int i = 0; i < j; i++)
           arr[i] = temp[i];

       return j;
   }
}

public class J_02_Quick_Sort {

   public static void main(String[] args) {
       quick_sort obj = new quick_sort();
       Scanner sc = new Scanner(System.in);

       System.out.print("Enter the number of elements: ");
       int n = sc.nextInt();

       int[] arr = new int[n];

       Random rand = new Random();
       for (int i = 0; i < n; i++) {
           arr[i] = rand.nextInt(100);
       }

       System.out.println("\nUnsorted Array: ");
       obj.printArr(arr, n);

       long startTime = System.nanoTime();
       obj.quickSort(arr, 0, n - 1);
       long endTime = System.nanoTime();

       long executionTime = (endTime - startTime);

       System.out.println("\nSorted Array in Ascending Order:");
       obj.printArr(arr, n);

       System.out.println("\nArray after Removing Duplicates:");
       int newlen = obj.removeDuplicates(arr, n);
       obj.printArr(arr, newlen);

       System.out.println("\nTime Taken for Quick Sort: " + executionTime + " nanoseconds");
   }
}
