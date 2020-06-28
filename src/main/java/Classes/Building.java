package Classes;

import java.util.ArrayList;
import java.util.List;


public class Building<T extends Passenger> {
    private final List<ContainerForFloors<T>> dispatchStoryContainersList;
    private final List<ContainerForFloors<T>> arrivalStoryContainersList;
    private final ContainerForPassengers<T> elevatorContainer;
    private final int storiesNumber;
    private final int elevatorCapacity;


    public Building(int storiesNumber, int elevatorCapacity) {
        super();
        this.dispatchStoryContainersList = new ArrayList<>(storiesNumber);
        this.arrivalStoryContainersList = new ArrayList<>(storiesNumber);
        for (int i = 0; i < storiesNumber; i++) {
            dispatchStoryContainersList.add(new ContainerForFloors<>(i));
            arrivalStoryContainersList.add(new ContainerForFloors<>(i));
        }
        this.elevatorContainer = new ContainerForPassengers<>();
        this.storiesNumber = storiesNumber;
        this.elevatorCapacity = elevatorCapacity;
    }

    
    public int getStoriesNumber() {
        return storiesNumber;
    }

    public ContainerForFloors<T> getDispatchContainer(int storyNumber) {
        return dispatchStoryContainersList.get(storyNumber);
    }

    public ContainerForFloors<T> getArrivalContainer(int storyNumber) {
        return arrivalStoryContainersList.get(storyNumber);
    }

    public ContainerForPassengers<T> getElevatorContainer() {
        return elevatorContainer;
    }

    public boolean isElevatorFull() {
        return elevatorContainer.getPassengersNumber() == elevatorCapacity;
    }

    public void addDispatchPassenger(int storyNumber, T t) {
        dispatchStoryContainersList.get(storyNumber).add(t);
    }

    public void addArrivalPassenger(int storyNumber, T t) {
        arrivalStoryContainersList.get(storyNumber).add(t);
    }

    public void addElevatorPassenger(T t) {
        elevatorContainer.add(t);
    }

    public void removeDispatchPassenger(int storyNumber, T t) {
        dispatchStoryContainersList.get(storyNumber).remove(t);
    }

    public void removeElevatorPassenger(T t) {
        elevatorContainer.remove(t);
    }

}
