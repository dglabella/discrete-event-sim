package gida.academics.labs.lab1.bootstrapping;

import gida.academics.labs.lab1.model.Entity;
import gida.academics.labs.lab1.model.Event;
import gida.academics.labs.lab1.utils.generators.Generator;

public class Arrival implements Planificable {

    private Event event;

    public Arrival(double clock, Entity entity, EventOrder order) {
        this.event = new Event(clock, entity, order);
    }

    @Override
    public Event getEvent() {
        return this.event;
    }

    @Override
    public void planificate(FutureEventList fel, Generator<Double> randomizer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'planificate'");
    }
}
