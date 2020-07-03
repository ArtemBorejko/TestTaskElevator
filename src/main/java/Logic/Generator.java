package Logic;

import Classes.Passenger;

import java.util.Random;

public class Generator {
    Random random = new Random();
    public int genNumberOfFloors(int min, int max){
        return random.nextInt((max - min) + 1) + min;
    }

    public int genNumberOfPassengers(){
        return random.nextInt(10);
    }

    public int genInitFloor(int n){
        return 1 + random.nextInt(n);
    }

    public Passenger[] generatePassengers(int initFloor, int n, int k){
        Passenger[] passengers = new Passenger[k];
        for(int i = 0; i < k; i++){
            passengers[i] = new Passenger(initFloor, n);
        }
        return passengers;
    }
}
