package Classes;

public class ContainerForFloors<T extends Passenger> extends ContainerForPassengers<T> {
    private int numberOfFloors;


    public ContainerForFloors(int numberOfFloors) {
        super();
        this.numberOfFloors = numberOfFloors;
    }


    public int getNumberOfFloors() {
        return numberOfFloors;
    }


    @Override
    public String toString() {
        return "numberOfFloors: " + numberOfFloors;
    }
}
