package Classes;

import Enums.Direction;

public class Elevator {
    private final int upperFloor;
    private Direction currentDirection;
    private int currentFloor;

    public Elevator(int storiesNumber) {
        this.upperFloor = storiesNumber - 1;
        this.currentFloor = 0;
        this.currentDirection = Direction.UP;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
    
    public int move() {
        switch (currentDirection) {
            case UP:
                goUp();
                break;
            case DOWN:
                goDown();
                break;
        }
        calculateDirection();
        return currentFloor;
    }

    private void calculateDirection() {
        if (currentFloor == 0) {
            currentDirection = Direction.UP;
        } else {
            if (currentFloor == upperFloor) {
                currentDirection = Direction.DOWN;
            }
        }
    }

    private void goUp() {
        currentFloor++;
    }

    private void goDown() {
        currentFloor--;
    }
}
