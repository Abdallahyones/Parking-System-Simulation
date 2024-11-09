import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        ParkingSimulation parkingSimulation = new ParkingSimulation();
        InputReader inputReader = new InputReader(parkingSimulation);
        ParkingLot parkingLot = new ParkingLot();
        Thread gate1 = new Thread(new Gate(1, // list of car1 )));
        Thread gate2 = new Thread(new Gate(2,// list of car 2)));
        Thread gate3 = new Thread(new Gate(3, // list of car 3)));

        // Start the gate threads
        gate1.start();
        gate2.start();
        gate3.start();

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