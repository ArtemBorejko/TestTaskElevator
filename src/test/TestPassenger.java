import Classes.Passenger;
import Enums.State;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestPassenger {
    Passenger passenger = new Passenger(1, 5);

    @Before
    public void setUp(){
        passenger.setDestinationFloor(2);
        passenger.setState(State.IN_MOVING);
    }

    @Test
    public void testDestinationFloor(){
        assertEquals(2, passenger.getDestinationFloor());
    }

    @Test
    public void testPassengerState(){
        assertEquals("IN_MOVING", passenger.getState().toString());
    }
}
