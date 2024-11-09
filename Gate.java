import java.util.List;

public class Gate implements Runnable {
    private final int gateId;
    private final List<Car> cars;

    public Gate(int gateId, List<Car> cars) {
        this.gateId = gateId;
        this.cars = cars;
    }

    @Override
    public void run() {
        for (Car car : cars) {
            new Thread(car).start();
        }
    }
}
