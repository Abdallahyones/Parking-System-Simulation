public class Car implements Runnable {
    private final int id;
    private final int gate;
    private final int arrivalTime;
    public final int parkingDuration;
    private final ParkingLot parkingLot;
    public Long waitingStartTime ;

    public Car(int id, int gate, int arrivalTime, int parkingDuration, ParkingLot parkingLot) {
        this.id = id;
        this.gate = gate;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            // Simulate arrival time
            Thread.sleep(arrivalTime * 1000);
            System.out.println(toString() + " arrived at time " + arrivalTime);

            waitingStartTime = System.currentTimeMillis();

            // Try to acquire a parking spot
            if (parkingLot.tryPark(this)) {
                // parked
            } else {
                parkingLot.park(this); // Wait for a spot to become available
            }

            Thread.sleep(parkingDuration * 1000); // Simulate parking duration
            parkingLot.leave(this);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Car " + id + " from Gate " + gate ;
    }
}
