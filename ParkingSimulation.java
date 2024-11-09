import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingSimulation {
    public List<Car> list;

    public ParkingSimulation() {
        this.list = new CopyOnWriteArrayList<>();
    }

    public void add(Car car) {
        list.add(car);
        list.sort(new CarComparator());
    }

    public boolean top(Car car) {
        if (list.isEmpty()) {
            return false;
        }
        return car.equals(list.get(0));
    }

    public void leave(Car car) {
        if (!list.isEmpty() && list.get(0).equals(car)) {
            list.remove(0);
        }
    }
}
