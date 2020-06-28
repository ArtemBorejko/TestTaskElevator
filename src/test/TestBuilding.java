import Classes.Building;
import Classes.Passenger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBuilding {
    Building<Passenger> building = new Building<>(5, 5);
    Passenger passenger = new Passenger(1, 5);
    Passenger passenger1 = new Passenger(3, 5);

    @Before
    public void setUp(){
        passenger.setDestinationFloor(2);
        passenger.setDestinationFloor(4);
        building.addDispatchPassenger(1, passenger);
        building.addDispatchPassenger(2, passenger1);
        building.addArrivalPassenger(passenger.getDestinationFloor(), passenger);
        building.addArrivalPassenger(passenger1.getDestinationFloor(), passenger1);
        building.addElevatorPassenger(passenger);
        building.addElevatorPassenger(passenger1);
    }

    @Test
    public void testElevator(){
        assertEquals(false, building.isElevatorFull());
    }
}
