package gida.academics.labs.lab1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import gida.academics.labs.lab1.bootstrapping.Arrival;
import gida.academics.labs.lab1.bootstrapping.EndOfService;
import gida.academics.labs.lab1.bootstrapping.EventOrder;
import gida.academics.labs.lab1.bootstrapping.FutureEventList;
import gida.academics.labs.lab1.scenarios.airport.entities.Aircraft;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void FelTestbyClock() {

        FutureEventList fel = new FutureEventList();
        fel.insert(new Arrival(45d, new Aircraft(0), EventOrder.ARRIVAL)); // 3
        fel.insert(new EndOfService(54d, new Aircraft(1), EventOrder.END_OF_SERVICE)); // 4
        fel.insert(new EndOfService(5d, new Aircraft(2), EventOrder.END_OF_SERVICE)); // 1
        fel.insert(new EndOfService(531d, new Aircraft(3), EventOrder.END_OF_SERVICE)); // 7
        fel.insert(new Arrival(320d, new Aircraft(4), EventOrder.ARRIVAL)); // 6
        fel.insert(new Arrival(54d, new Aircraft(5), EventOrder.ARRIVAL)); // 4
        fel.insert(new Arrival(5d, new Aircraft(6), EventOrder.ARRIVAL)); // 2

        assertEquals(5d, fel.getImminent().getEvent().getClock(), 0);
        assertEquals(5d, fel.getImminent().getEvent().getClock(), 0);
        assertEquals(45d, fel.getImminent().getEvent().getClock(), 0);
        assertEquals(54d, fel.getImminent().getEvent().getClock(), 0);
        assertEquals(54d, fel.getImminent().getEvent().getClock(), 0);
        assertEquals(320d, fel.getImminent().getEvent().getClock(), 0);
        assertEquals(531d, fel.getImminent().getEvent().getClock(), 0);

    }

    @Test
    public void FelTestbyID() {

        FutureEventList fel = new FutureEventList();
        fel.insert(new Arrival(45d, new Aircraft(0), EventOrder.ARRIVAL)); // 2
        fel.insert(new EndOfService(54d, new Aircraft(1), EventOrder.END_OF_SERVICE)); // 3
        fel.insert(new EndOfService(5d, new Aircraft(2), EventOrder.END_OF_SERVICE)); // 1
        fel.insert(new EndOfService(531d, new Aircraft(3), EventOrder.END_OF_SERVICE)); // 6
        fel.insert(new Arrival(320d, new Aircraft(4), EventOrder.ARRIVAL)); // 5
        fel.insert(new Arrival(54d, new Aircraft(5), EventOrder.ARRIVAL)); // 4

        assertEquals(2, fel.getImminent().getEvent().getEntity().getId(), 0);
        assertEquals(6, fel.getImminent().getEvent().getEntity().getId(), 0);
        assertEquals(0, fel.getImminent().getEvent().getEntity().getId(), 0);
        assertEquals(1, fel.getImminent().getEvent().getEntity().getId(), 0);
        assertEquals(5, fel.getImminent().getEvent().getEntity().getId(), 0);
        assertEquals(4, fel.getImminent().getEvent().getEntity().getId(), 0);
        assertEquals(3, fel.getImminent().getEvent().getEntity().getId(), 0);

    }
}
