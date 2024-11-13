import java.util.PriorityQueue;

public class J_00_Poll {

    /*
     * The poll() method retrieves and removes the head (the smallest element) of the priority queue.
     * If the queue is empty, it returns null.
     * Works with queue.
     */
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(20);
        pq.add(15);

        // Poll elements from the PriorityQueue
        System.out.println("Head of the queue: " + pq.poll()); // Output: 10
        System.out.println("Next head of the queue: " + pq.poll()); // Output: 15
        System.out.println("Next head of the queue: " + pq.poll()); // Output: 20
        System.out.println("Polling from an empty queue: " + pq.poll()); // Output: null
    }
}