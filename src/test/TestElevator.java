import Classes.Elevator;
import Enums.Direction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestElevator {
    Elevator elevator = new Elevator(5);

    @Before
    public void setUp(){
        elevator.move();
    }

    @Test
    public void testMoving(){
        assertEquals(Direction.UP, elevator.getCurrentDirection());
    }

    @Test
    public void testDoMove(){
        elevator.move();
        assertEquals(3, elevator.move());
    }
}
