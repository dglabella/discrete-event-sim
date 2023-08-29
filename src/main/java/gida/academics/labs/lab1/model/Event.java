package gida.academics.labs.lab1.model;

import java.io.Serializable;
import gida.academics.labs.lab1.bootstrapping.EventOrder;

/**
 * This class represents an event in the simulation.
 * 
 */
public final class Event implements Serializable {

    private final double clock;
    private final EventOrder order;

    private final Entity entity;

    /**
     * Constructs an event for the simulation.
     * 
     * @param clock The time when it occurs.
     * @param entity The entity in which the event occurs.
     * @param order The value type for the event.
     */
    public Event(double clock, Entity entity, EventOrder order) {
        this.clock = clock;
        this.entity = entity;
        this.order = order;
    }

    public double getClock() {
        return this.clock;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public EventOrder getOrder() {
        return this.order;
    }

    @Override
    public String toString() {
        return "{\"clock\":" + this.clock + ", \"order\":" + this.order + ",\"entity\":"
                + this.entity.toString() + "}";
    }
}
