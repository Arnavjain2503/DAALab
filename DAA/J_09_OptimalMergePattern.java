package DSA.DAA;
import java.util.*;

public class J_09_OptimalMergePattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Files: ");
        int n = sc.nextInt();

        // Use a PriorityQueue to create a min-heap
        System.out.println("Enter the sizes of the files: ");

        PriorityQueue<Integer> minheap = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            int element =sc.nextInt();
            minheap.add(element );  
        }

        int ans = 0;
        while (minheap.size() > 1) {
            // Extract the two smallest elements
            int e1 = minheap.poll();  
            int e2 = minheap.poll();

            // Calculate the cost of merging these two files
            ans += e1 + e2;

            // Insert the merged file back into the min-heap
            minheap.add(e1 + e2);  
        }
        System.out.println("The minimum cost of merging the files is: " + ans);
    }
}