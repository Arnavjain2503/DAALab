package DSA.DAA;

import java.util.*;

public class J_09_OptimalMergePattern_q {
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

    public static void mergeCost() {
        // List of file sizes and multipliers (size, multiplier)
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Files: ");
        int n = sc.nextInt();

        // Use a PriorityQueue to create a min-heap based on the cost of merging files
        PriorityQueue<File> files = new PriorityQueue<>(new Comparator<File>() {
            public int compare(File f1, File f2) {
                return Double.compare(f1.size * f1.multiplier, f2.size * f2.multiplier);
            }
        });

        System.out.println("Enter the sizes and multipliers of the files: ");
        for (int i = 0; i < n; i++) {
            int size = sc.nextInt();
            double multiplier = sc.nextDouble();
            files.add(new File(size, multiplier, "File " + (i + 1)));
        }

        double totalCost = 0;
        int maxMergeOperations = 10;
        boolean sizeExceeded = false;

        while (files.size() > 1 && maxMergeOperations > 0) {
            // Poll the two smallest files
            File f1 = files.poll();
            File f2 = files.poll();

            System.out.println();
            System.out.println("Merging files: (" + f1.size + ", " + f1.multiplier + ") and (" + f2.size + ", " + f2.multiplier + ")");

            // Calculate the merged size and cost
            int mergedSize = f1.size + f2.size;
            double mergeCost = (f1.size * f1.multiplier) + (f2.size * f2.multiplier);
            System.out.println("Merged Size: " + mergedSize);
            System.out.println("Intermediate cost: " + mergeCost);

            // If the merged size exceeds the limit, stop the process
            if (mergedSize > 500) {
                sizeExceeded = true;
                break;
            }

            // Add the cost to totalCost
            totalCost += mergeCost;

            // Create the new merged file with the average multiplier
            files.add(new File(mergedSize, (f1.multiplier + f2.multiplier) / 2.0, f1.name + "+" + f2.name));

            // Decrease the number of remaining merge operations
            maxMergeOperations--;

            // Display the updated list of files
            System.out.println("Updated list of files:");
            for (File f : files) {
                System.out.println("Size: " + f.size + ", Multiplier: " + f.multiplier + ", Name: " + f.name);
            }

            System.out.println("\nNext Iteration:");
        }

        // Output the total cost
        System.out.println("The minimum cost of merging the files is: " + totalCost);

        // Check for termination conditions
        if (maxMergeOperations == 0) {
            System.out.println("Maximum number of merge operations exceeded");
        }
        else if (sizeExceeded) {
            System.out.println("Maximum size exceeded");
        }
    }

    public static void main(String[] args) {
        mergeCost();
    }
}
