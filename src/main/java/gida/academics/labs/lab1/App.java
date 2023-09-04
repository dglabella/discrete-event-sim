package gida.academics.labs.lab1;

import gida.academics.labs.lab1.bootstrapping.Arrival;
import gida.academics.labs.lab1.bootstrapping.EndOfService;
import gida.academics.labs.lab1.bootstrapping.EventOrder;
import gida.academics.labs.lab1.bootstrapping.FutureEventList;
import gida.academics.labs.lab1.scenarios.airport.entities.Aircraft;

/**
 * Hello world! d
 */
public class App {
    public static void main(String[] args) {
        FutureEventList fel = new FutureEventList();
        fel.insert(new Arrival(45d, new Aircraft(0), EventOrder.ARRIVAL)); // 2
        fel.insert(new EndOfService(54d, new Aircraft(1), EventOrder.END_OF_SERVICE)); // 3
        fel.insert(new EndOfService(5d, new Aircraft(2), EventOrder.END_OF_SERVICE)); // 1
        fel.insert(new EndOfService(531d, new Aircraft(3), EventOrder.END_OF_SERVICE)); // 6
        fel.insert(new Arrival(320d, new Aircraft(4), EventOrder.ARRIVAL)); // 5
        fel.insert(new Arrival(54d, new Aircraft(5), EventOrder.ARRIVAL)); // 4

        System.out.println(fel);
    }
}
