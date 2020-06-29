package Logic;

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
}
