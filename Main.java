import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        // Initialize parking lot
        ParkingLot parkingLot = new ParkingLot(4);

        // Read input data and organize cars by gates
        Map<Integer, List<Car>> gateCarsMap = InputReader.readInputFile("input.txt", parkingLot);
        List<Car> list1 =  gateCarsMap.getOrDefault(1, List.of())  ;
        List<Car> list2 =  gateCarsMap.getOrDefault(2, List.of())  ;
        List<Car> list3 =  gateCarsMap.getOrDefault(3, List.of())  ;
        Thread gate1 = new Thread(new Gate(1, list1));
        Thread gate2 = new Thread(new Gate(2, list2));
        Thread gate3 = new Thread(new Gate(3, list3));

        // Start the gate threads
        gate1.start();
        gate2.start();
        gate3.start();

        // Wait for all gates to finish
        try {
            gate1.join();
            gate2.join();
            gate3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Optionally: Wait for all threads to finish (not required but ensures completion)
//        try {
//            Thread.sleep(10000); // Sleep for 10 seconds or adjust based on your simulation needs
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }

        // Print Details
        System.out.println("Total Cars Served: " + parkingLot.getTotalCarsServed());
        System.out.println("Current Cars in Parking: " + parkingLot.getCurrentCarsInParking());
        System.out.println("Details:");
        System.out.println("- Gate 1 served "+ list1.size() + " cars.");
        System.out.println("- Gate 2 served "+ list2.size() + " cars.");
        System.out.println("- Gate 3 served "+ list3.size() + " cars.");
    }
}
