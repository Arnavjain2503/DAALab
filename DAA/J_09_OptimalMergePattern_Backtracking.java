package DSA.DAA;
import java.util.*;

public class J_09_OptimalMergePattern_Backtracking {
    static int minCost = Integer.MAX_VALUE; // To store the minimum cost

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Files: ");
        int n = sc.nextInt();

        // Input the sizes of the files
        System.out.println("Enter the sizes of the files: ");
        ArrayList<Integer> files = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            files.add(sc.nextInt());
        }

        // Start the backtracking process with initial cost = 0
        findOptimalMerge(files, 0);

        // Output the minimum cost found
        System.out.println("The minimum cost of merging the files is: " + minCost);
    }

    // Backtracking function to find the minimum cost of merging the files
    public static void findOptimalMerge(ArrayList<Integer> files, int currentCost) {
        // Base case: If there is only one file left, update the minimum cost
        if (files.size() == 1) {
            minCost = Math.min(minCost, currentCost);
            return;
        }

        // Try merging every pair of files and recursively solve the problem
        for (int i = 0; i < files.size(); i++) {
            for (int j = i + 1; j < files.size(); j++) {
                // Merge files[i] and files[j]
                int mergeCost = files.get(i) + files.get(j);
                int newCost = currentCost + mergeCost;

                // Create a new list of files with the merged file
                ArrayList<Integer> newFiles = new ArrayList<>(files);
                newFiles.remove(j); // Remove the larger index first to avoid index shift
                newFiles.remove(i); // Remove the smaller index
                newFiles.add(mergeCost); // Add the merged file

                // Recursively find the cost for the new set of files
                findOptimalMerge(newFiles, newCost);

                // Backtracking happens automatically as we explore all paths
            }
        }
    }
}
