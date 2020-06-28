package Classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContainerForPassengers<T extends Passenger> implements Iterable<T>{
    private List<T> passengersList = new ArrayList<T>();

    public void add(T t){
        passengersList.add(t);
    }

    public void remove(T t){
        passengersList.remove(t);
    }

    public int getPassengersNumber(){
        return passengersList.size();
    }


    public Iterator<T> iterator(){
        return passengersList.iterator();
    }

    @Override
    public String toString(){
        return "PassengerList " + passengersList;
    }
}
