import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Gate implements Runnable {
    private final int gateId;
    private final List<Car> cars;

    public Gate(int gateId, List<Car> cars) {
        this.gateId = gateId;
        this.cars = cars;
    }

    @Override
    public void run() {
        // Create a CountDownLatch with the number of cars
        CountDownLatch latch = new CountDownLatch(cars.size());

        for (Car car : cars) {
            new Thread(() -> {
                try {
                    car.run(); // Run the car logic
                } finally {
                    latch.countDown(); // Signal completion
                }
            }).start();
        }
        try {
            latch.await(); // Wait for all cars to finish
            System.out.println("Gate " + gateId + " has finished processing all cars.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Gate " + gateId + " was interrupted.");
        }
    }
}
