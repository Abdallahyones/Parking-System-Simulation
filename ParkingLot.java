import java.util.concurrent.Semaphore;

public class ParkingLot {
    private final Semaphore spots;
    private final int totalSpots;
    private int totalCarsServed = 0;

    public ParkingLot(int totalSpots) {
        this.totalSpots = totalSpots;
        this.spots = new Semaphore(totalSpots, true); // 4 slots
    }
    public boolean tryPark() {
        return spots.tryAcquire();
    }

    public void park() {
        try {
            spots.acquire(); // Acquire a parking spot
//            synchronized (this) {
//                totalCarsServed++;
//            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void leave() {
        totalCarsServed++;
        spots.release(); // Release a parking spot
    }

    public synchronized int getTotalCarsServed() {
        return totalCarsServed;
    }

    public synchronized int getCurrentCarsInParking() {
        return totalSpots - spots.availablePermits();
    }
}
