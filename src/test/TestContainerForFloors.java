import Classes.ContainerForFloors;
import Classes.Passenger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestContainerForFloors {
    Passenger passenger = new Passenger(1, 5);
    Passenger passenger1 = new Passenger(3, 5);
    ContainerForFloors<Passenger> container = new ContainerForFloors<>(5);

    @Before
    public void setUp(){
        container.add(passenger1);
        container.add(passenger);
    }

    @Test
    public void TestAddPassengersOnFloor(){
        assertEquals(2, container.getPassengersNumber());
        container.remove(passenger);
        assertEquals(1, container.getPassengersNumber());
    }
}
