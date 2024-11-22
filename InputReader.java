import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputReader {
    public static Map<Integer, List<Car>> readInputFile(String filename, ParkingLot parkingLot) {
        Map<Integer, List<Car>> gateCarsMap = new HashMap<>();  // Map to store cars by gate

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length != 4) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }

                // Parse gate, car ID, arrival time, and parking duration
                int gate = Integer.parseInt(parts[0].split(" ")[1]);
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkingDuration = Integer.parseInt(parts[3].split(" ")[1]);

                // Create a new Car object
                Car car = new Car(carId, gate, arrivalTime, parkingDuration, parkingLot);

                // Add the car to the appropriate gate list in the map
                gateCarsMap.computeIfAbsent(gate, k -> new ArrayList<>()).add(car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gateCarsMap;
    }
}
