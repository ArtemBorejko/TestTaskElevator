package Logic;

import Classes.ContainerForFloors;
import Classes.ContainerForPassengers;
import Classes.Passenger;
import Controllers.MainController;
import Enums.State;

public class TransportationTask implements Runnable {
    private final MainController maincontroller;
    private final ContainerForFloors<Passenger> dispatchStoryContainer;
    private final ContainerForPassengers<Passenger> elevatorContainer;
    private final Passenger passenger;

    public TransportationTask(MainController maincontroller, ContainerForFloors<Passenger> dispatchStoryContainer,
                              ContainerForPassengers<Passenger> elevatorContainer, Passenger passenger) {
        this.maincontroller = maincontroller;
        this.dispatchStoryContainer = dispatchStoryContainer;
        this.elevatorContainer = elevatorContainer;
        this.passenger = passenger;
        passenger.setState(State.IN_MOVING);
    }

    public void run() {
        boolean operationSuccess = false;
        try {
            synchronized (dispatchStoryContainer) {
                maincontroller.decBarrier();
                while (!operationSuccess && !Thread.currentThread().isInterrupted()) {
                    dispatchStoryContainer.wait();
                    operationSuccess = maincontroller.requestBoard(passenger);
                }
            }
            operationSuccess = false;
            synchronized (elevatorContainer) {
                maincontroller.decBarrier();
                while (!operationSuccess && !Thread.currentThread().isInterrupted()) {
                    elevatorContainer.wait();
                    operationSuccess = maincontroller.requestDeboard(passenger);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (Thread.currentThread().isInterrupted()) {
            passenger.setState(State.ABORTED);
        } else {
            passenger.setState(State.COMPLETED);
        }
    }
}
