import java.util.*;

public class J_11_ActivitySelection {

    // Custom comparator to sort the meetings based on their end times
    static class MeetingComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] t1, int[] t2) {
            if (t1[1] == t2[1]) {
                return Integer.compare(t1[0], t2[0]); // Sort by start time if end times are equal
            }
            return Integer.compare(t1[1], t2[1]); // Sort by end time
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Jobs: ");
        int n = sc.nextInt();

        // List to store start and end times of meetings along with job numbers
        List<int[]> times = new ArrayList<>();

        System.out.println("Enter start and finish time for each job: ");
        for (int i = 0; i < n; i++) {
            int st = sc.nextInt();
            int fn = sc.nextInt();
            times.add(new int[]{st, fn, i + 1}); // Adding job number (i+1)
        }

        // Sort meetings based on the custom comparator
        times.sort(new MeetingComparator());

        int ans = 1;
        int previousEndTime = times.get(0)[1];
        List<Integer> selectedJobs = new ArrayList<>();
        selectedJobs.add(times.get(0)[2]); // Add the first job to the list

        // Iterate through the sorted meetings to count and select the maximum number of non-overlapping meetings
        for (int i = 1; i < n; i++) {
            if (times.get(i)[0] >= previousEndTime) {
                ans++;
                previousEndTime = times.get(i)[1];
                selectedJobs.add(times.get(i)[2]); // Add the selected job to the list
            }
        }

        System.out.println("Maximum number of jobs that can be scheduled: " + ans);
        System.out.println("Scheduled jobs: " + selectedJobs);
    }
}
