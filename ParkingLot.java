import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingLot {

    public int slot ;
    static private int totalCars ;

    public ParkingLot() {
        slot = 0 ;
        totalCars = 0 ;
    }
    public boolean parkingslot() {
        return slot < 4 ;
    }
    public void enterParking() {
        totalCars++;
        slot++;
    }

    public void leaveParking() {
        slot--;
    }

    public int getTotalCarsServed() {
        return totalCars;
    }
}
