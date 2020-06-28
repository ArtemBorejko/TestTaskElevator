package Controllers;

import Classes.Building;
import Classes.ContainerForPassengers;
import Classes.Elevator;
import Classes.Passenger;

public class MainController {
    private final Building<Passenger> building;
    private final Elevator elevator;
    private final int initialPassengerNumber;
    private int loop;
    private int barrier;
    private int remainPassengersNumber;
    private int currentStory;
    private Passenger bufferPassenger;

    public MainController(Building<Passenger> building, int passengersNumber) {
        this.building = building;
        this.initialPassengerNumber = passengersNumber;
        this.remainPassengersNumber = passengersNumber;
        elevator = new Elevator(building.getStoriesNumber());
    }

    public int getInitialPassengersNumber() {
        return initialPassengerNumber;
    }

    public void doWork() {
        synchronized (this) {
            barrier = barrier + initialPassengerNumber;
        }
        while (remainPassengersNumber != 0 && !Thread.currentThread().isInterrupted()) {
            try {
                deboard();
                board();
                currentStory = elevator.move();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void decBarrier() {
        this.notifyAll();
        barrier--;
    }

    public synchronized boolean requestDeboard(Passenger passenger) throws InterruptedException {
        if (passenger.getDestinationFloor() == currentStory) {
            while (bufferPassenger != null && !Thread.currentThread().isInterrupted()) {
                this.wait();
            }
            bufferPassenger = passenger;
            decLoopNotify();
            return true;
        } else {
            decLoopNotify();
            return false;
        }
    }

    public synchronized boolean requestBoard(Passenger passenger) throws InterruptedException {
        if (passenger.getDirection().equals(elevator.getCurrentDirection())) {
            while (bufferPassenger != null && !Thread.currentThread().isInterrupted()) {
                this.wait();
            }
            if (!building.isElevatorFull()) {
                bufferPassenger = passenger;
                decLoopNotify();
                incBarrier();
                return true;
            } else {
                decLoopNotify();
                return false;
            }
        } else {
            decLoopNotify();
            return false;
        }
    }

    private synchronized void deboard() throws InterruptedException {
        while (barrier != 0 && !Thread.currentThread().isInterrupted()) {
            this.wait();
        }
        notifyPassengersSetLoop(building.getElevatorContainer());
        while ((loop != 0 || bufferPassenger != null) && !Thread.currentThread().isInterrupted()) {
            while (bufferPassenger == null && loop != 0 && !Thread.currentThread().isInterrupted()) {
                this.wait();
            }
            if (bufferPassenger != null) {
                building.removeElevatorPassenger(bufferPassenger);
                building.addArrivalPassenger(currentStory, bufferPassenger);
                bufferPassenger = null;
                remainPassengersNumber--;
                this.notifyAll();
            }
        }
    }

    private synchronized void board() throws InterruptedException {
        notifyPassengersSetLoop(building.getDispatchContainer(currentStory));
        while ((loop != 0 || bufferPassenger != null) && !Thread.currentThread().isInterrupted()) {
            while (bufferPassenger == null && loop != 0 && !Thread.currentThread().isInterrupted()) {
                this.wait();
            }
            if (bufferPassenger != null) {
                building.removeDispatchPassenger(currentStory, bufferPassenger);
                building.addElevatorPassenger(bufferPassenger);
                bufferPassenger = null;
                this.notifyAll();
            }
        }
    }

    private void notifyPassengersSetLoop(ContainerForPassengers<Passenger> storyContainer) {
        synchronized (storyContainer) {
            storyContainer.notifyAll();
            loop = storyContainer.getPassengersNumber();
        }
    }

    private void decLoopNotify() {
        this.notifyAll();
        loop--;
    }

    private void incBarrier() {
        barrier++;
    }
}
