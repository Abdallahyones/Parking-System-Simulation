import java.util.PriorityQueue;
import java.util.Comparator;

public class Spots {
    public int permits;
    private final PriorityQueue<Car> waitingQueue; // Queue to maintain order based on arrival

    public Spots(int permits) {
        this.permits = permits;
        this.waitingQueue = new PriorityQueue<>(Comparator.comparingLong(car -> car.waitingStartTime));
    }

    public synchronized void acquire(Car car) throws InterruptedException {
        waitingQueue.add(car); // Add the car to the queue
        while (permits <= 0 || waitingQueue.peek() != car) {
            wait(); // Wait until a permit is available and it's this car's turn
        }
        waitingQueue.poll(); // Remove the car from the queue
        permits--;

        long waitingTime = (System.currentTimeMillis() - car.waitingStartTime) / 1000; // Calculate waiting time
        if (waitingTime > 0)
            System.out.println(car.toString() + " parked after waiting " + waitingTime +
                    " seconds. (Parking Status: " + permits + " spots occupied)");
        else
            System.out.println(car.toString() + " (Parking Status: " + (4-permits) + " spots occupied)");
    }

    public synchronized void acquireUnchecked(Car car) {
        if (permits > 0) {
            permits--; // Decrement without waiting
            System.out.println(car.toString() + " parked immediately. (Parking Status: " + (4-permits) + " spots occupied)");
        }
    }

    public synchronized void release(Car car) {
        permits++;
        System.out.println(car.toString() + " left after " + car.parkingDuration + " units of time. (Parking Status: " + (4-permits) + " spots occupied)");
        notifyAll(); // Notify all waiting threads
    }

    public synchronized int availablePermits() {
        return permits;
    }
}
