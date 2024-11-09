import java.util.Comparator;

public class Car implements Runnable {
    private final int id;
    private boolean parking ;
    public final int gate;
    public final int arrivalTime ;

    private final int parkingDuration;
    private final ParkingLot parkingLot;
    private final ParkingSimulation parkingSimulation;
    private int waiting ;

    public Car(int id, int gate, int arrivalTime, int parkingDuration, ParkingLot parkingLot, ParkingSimulation parkingSimulation) {
        this.id = id;
        this.gate = gate;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.parkingLot = parkingLot;
        this.parkingSimulation = parkingSimulation;
        this.parking = false;
        this.waiting = 0 ;
    }

    @Override
    public void run() {
        try {

            Thread.sleep(arrivalTime * 1000);
            System.out.println(toString() + " arrived at time " + arrivalTime);

            parkingSimulation.add(this);

            while (!parkingLot.parkingslot()) { // error
                if (waiting == 0) {
                    System.out.println(toString() + " waiting for a spot.");
                }
                Thread.sleep(1000);
                waiting++;
            }

            parkingLot.enterParking();
            if (waiting > 0) {
                System.out.println(toString() + " parked after waiting for " + waiting + " units of time. (Parking Status:" + parkingLot.slot + " spots occupied)");
            } else {
                System.out.println(toString() + " parked.(Parking Status:" + parkingLot.slot + " spots occupied)");
            }


            Thread.sleep(parkingDuration * 1000);

            parkingLot.leaveParking();
            System.out.println(toString() + " left after " + parkingDuration + " units of time. (Parking Status: " + parkingLot.slot + " spots occupied)");

            parkingSimulation.leave(this);  // Remove the car from the waiting list
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Car " + id + " from Gate " + gate;
    }
}

class CarComparator implements Comparator<Car> {
    @Override
    public int compare(Car p1, Car p2) {
        if (p1.arrivalTime != p2.arrivalTime) {
            return Integer.compare(p1.arrivalTime, p2.arrivalTime);
        } else {
            return Integer.compare(p1.gate, p2.gate);
        }
    }
}

