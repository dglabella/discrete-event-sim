package gida.academics.labs.lab1.bootstrapping;

import gida.academics.labs.lab1.model.Entity;
import gida.academics.labs.lab1.model.Event;
import gida.academics.labs.lab1.model.WorldState;
import gida.academics.labs.lab1.model.engine.StatisticsComputer;
import gida.academics.labs.lab1.utils.generators.Generator;

public class EndOfService implements Event {

    private final double clock;
    private final Entity entity;
    private final int priority;

    public EndOfService(double clock, Entity entity, int priority) {
        this.clock = clock;
        this.entity = entity;
        this.priority = priority;
    }

    @Override
    public double getClock() {
        return this.clock;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public boolean planificate(FutureEventList fel, WorldState worldState,
            StatisticsComputer statisticsComputer, Generator<Double> randomizer) {}
}
