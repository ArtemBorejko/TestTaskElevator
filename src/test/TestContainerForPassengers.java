import Classes.ContainerForPassengers;
import Classes.Passenger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestContainerForPassengers {
    Passenger passenger = new Passenger(1, 5);
    Passenger passenger1 = new Passenger(3, 5);
    ContainerForPassengers<Passenger> container = new ContainerForPassengers<>();

    @Before
    public void setUp(){
        container.add(passenger1);
        container.add(passenger);
    }

    @Test
    public void TestAddPassengersInContainer(){
        assertEquals(2, container.getPassengersNumber());
        container.remove(passenger);
        assertEquals(1, container.getPassengersNumber());
    }
}
