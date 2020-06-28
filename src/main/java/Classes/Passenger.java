package Classes;

import Enums.Direction;
import Enums.State;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Passenger {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    private int id;
    private Direction direction;
    private int destinationFloor;
    private State state;

    public Passenger(int initFloor, int floors) {
        super();
        this.id = idGenerator.getAndIncrement();
        this.destinationFloor = calculateDestinationFloor(initFloor, floors);
        state = State.NOT_STARTED;
        if(destinationFloor < initFloor){
            direction = Direction.DOWN;
        }else {
            direction = Direction.UP;
        }
    }

    public int getId(){
        return id;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString(){
        return  "Id: " + id +
                "\nDirection: " + direction +
                "\nDestinationFloor:" + destinationFloor;
    }

    public int calculateDestinationFloor(int initFloor, int floors){
        Random random = new Random();
        int destinationFloor = random.nextInt(floors);
        while(destinationFloor == initFloor){
            destinationFloor = random.nextInt(floors);
        }
        return destinationFloor;
    }
}
