public class Car implements Runnable {
    private final int id;
    private final int gate;
    private final int arrivalTime;
    private final int parkingDuration;
    private final ParkingLot parkingLot;

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
            System.out.println("Car " + id + " from Gate " + gate + " arrived at time " + arrivalTime);

            long waitingStartTime = System.currentTimeMillis(); // Record the waiting start time

            // Try to acquire a parking spot
            if (parkingLot.tryPark()) {
                System.out.println("Car " + id + " from Gate " + gate + " parked immediately. (Parking Status: " +
                        parkingLot.getCurrentCarsInParking() + " spots occupied)");
            } else {
                System.out.println("Car " + id + " from Gate " + gate + " waiting for a spot.");
                parkingLot.park(); // Wait for a spot to become available
                long waitingTime = (System.currentTimeMillis() - waitingStartTime) / 1000; // Calculate waiting time
                if (waitingTime > 0)
                    System.out.println("Car " + id + " from Gate " + gate + " parked after waiting " + waitingTime +
                            " seconds. (Parking Status: " + parkingLot.getCurrentCarsInParking() + " spots occupied)");
                else
                    System.out.println("Car " + id + " from Gate " + gate +
                            " (Parking Status: " + parkingLot.getCurrentCarsInParking() + " spots occupied)");
            }

            Thread.sleep(parkingDuration * 1000); // Simulate parking duration
            parkingLot.leave();
            System.out.println("Car " + id + " from Gate " + gate + " left after " + parkingDuration +
                    " units of time. (Parking Status: " + parkingLot.getCurrentCarsInParking() + " spots occupied)");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
