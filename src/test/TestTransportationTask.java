import Classes.Building;
import Classes.ContainerForFloors;
import Classes.ContainerForPassengers;
import Classes.Passenger;
import Controllers.MainController;
import Logic.TransportationTask;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestTransportationTask {
    Building building = new Building(5, 5);
    MainController mainController = new MainController(building, 5);
    ContainerForPassengers<Passenger> container = new ContainerForPassengers<>();
    ContainerForFloors<Passenger> container1 = new ContainerForFloors<>(5);
    Passenger passenger = new Passenger(1, 5);
    Passenger passenger1 = new Passenger(2, 5);
    TransportationTask transportationTask = new TransportationTask(mainController, container1, container, passenger);

    @Before
    public void setUp(){
        passenger.setDestinationFloor(3);
        container.add(passenger);
        container.add(passenger1);
        container1.add(passenger1);
        container1.add(passenger);
        building.addElevatorPassenger(passenger);
        building.addDispatchPassenger(1, passenger);
        building.addDispatchPassenger(2, passenger1);
        building.addArrivalPassenger(4, passenger);
        building.addArrivalPassenger(3, passenger1);
        transportationTask.run();
    }

    @Test
    public void testDestinationFloor(){
        assertEquals("COMPLETED", passenger.getState().toString());
    }
}
