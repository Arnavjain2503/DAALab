package DSA.DAA;

import java.util.*;

public class J_09_OptimalMergePattern_Backtracking_q {

    public static class File {
        int size;
        double multiplier;
        String name;

        File(int size, double multiplier, String name) {
            this.size = size;
            this.multiplier = multiplier;
            this.name = name;
        }
    }

    public static double minTotalCost = Double.MAX_VALUE;
    public static boolean sizeExceeded = false;
    public static boolean maxOperationsExceeded = false;

    // Backtracking method to find the optimal merge sequence
    public static void mergeCost(List<File> files, double currentCost, int operationsLeft) {
        if (files.size() == 1) {
            // If only one file remains, check if the current sequence has a lower cost
            if (currentCost < minTotalCost) {
                minTotalCost = currentCost;
            }
            return;
        }

        if (operationsLeft <= 0) {
            // If no operations are left, mark as exceeded and backtrack
            maxOperationsExceeded = true;
            return;
        }

        // Try merging each pair of files
        for (int i = 0; i < files.size() - 1; i++) {
            for (int j = i + 1; j < files.size(); j++) {
                File f1 = files.get(i);
                File f2 = files.get(j);

                // Calculate the merged size and cost
                int mergedSize = f1.size + f2.size;
                double mergeCost = (f1.size * f1.multiplier) + (f2.size * f2.multiplier);

                // If the merged size exceeds the limit, backtrack
                if (mergedSize > 500) {
                    sizeExceeded = true;
                    continue;
                }

                // Create a new file for the merged result
                File mergedFile = new File(mergedSize, (f1.multiplier + f2.multiplier) / 2.0, f1.name + "+" + f2.name);

                // Create a new list of files excluding the two merged files
                List<File> newFiles = new ArrayList<>(files);
                newFiles.remove(f1);
                newFiles.remove(f2);
                newFiles.add(mergedFile);

                // Recur with the updated list, adding the merge cost
                mergeCost(newFiles, currentCost + mergeCost, operationsLeft - 1);
            }
        }
    }

    public static void main(String[] args) {
        // List of file sizes and multipliers
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Files: ");
        int n = sc.nextInt();

        List<File> files = new ArrayList<>();
        System.out.println("Enter the sizes and multipliers of the files: ");
        for (int i = 0; i < n; i++) {
            int size = sc.nextInt();
            double multiplier = sc.nextDouble();
            files.add(new File(size, multiplier, "File " + (i + 1)));
        }

        int maxMergeOperations = 10;
        minTotalCost = Double.MAX_VALUE; // Reset the minimum cost
        sizeExceeded = false;            // Reset size flag
        maxOperationsExceeded = false;   // Reset merge operations flag

        // Call the backtracking method
        mergeCost(files, 0, maxMergeOperations);

        // Output the results
        if (sizeExceeded) {
            System.out.println("Maximum size exceeded during merging.");
        } else if (maxOperationsExceeded) {
            System.out.println("Maximum number of merge operations exceeded.");
        } else {
            System.out.println("The minimum cost of merging the files is: " + minTotalCost);
        }
    }
}
