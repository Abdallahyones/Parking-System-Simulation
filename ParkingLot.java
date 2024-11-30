public class ParkingLot {
    private final Spots spots;
    private final int totalSpots;
    private int totalCarsServed = 0;

    public ParkingLot(int totalSpots) {
        this.totalSpots = totalSpots;
        this.spots = new Spots(totalSpots);
    }

    public synchronized boolean tryPark(Car A) {
        if (spots.availablePermits() > 0) {
            spots.acquireUnchecked(A);
            return true;
        }
        return false;
    }

    public void park(Car A) {
        try {
            spots.acquire(A); // Wait for a spot to become available
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void leave(Car A) {
        totalCarsServed++;
        spots.release(A); // Release a parking spot and notify waiting cars
    }

    public synchronized int getTotalCarsServed() {
        return totalCarsServed;
    }

    public synchronized int getCurrentCarsInParking() {
        return totalSpots - spots.availablePermits();
    }
}
