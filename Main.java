import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Initialize the parking simulation and parking lot
        ParkingSimulation parkingSimulation = new ParkingSimulation();
        ParkingLot parkingLot = new ParkingLot();

        // Read the input file and parse cars by gates
        Map<Integer, List<Car>> gateCarsMap = InputReader.readInputFile("input.txt", parkingLot , parkingSimulation);

        // Create threads for each gate
        Thread gate1 = new Thread(new Gate(1, gateCarsMap.getOrDefault(1, List.of())));
        Thread gate2 = new Thread(new Gate(2, gateCarsMap.getOrDefault(2, List.of())));
        Thread gate3 = new Thread(new Gate(3, gateCarsMap.getOrDefault(3, List.of())));

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

        // Output the final report
        System.out.println("Total Cars Served: " + parkingLot.getTotalCarsServed());
    }
}
