package gida.academics.labs.lab1.model;

import gida.academics.labs.lab1.bootstrapping.FutureEventList;
import gida.academics.labs.lab1.model.engine.StatisticsComputer;
import gida.academics.labs.lab1.utils.generators.Generator;

/**
 * This interface represents an event in the simulation.
 * 
 */
public interface Event {

    /**
     * returns the ocurrence clock of this event.
     * 
     * @return the clock.
     */
    double getClock();

    /**
     * 
     * @return
     */
    int getPriority();

    /**
     * 
     * @return
     */
    Entity getEntity();

    /**
     * 
     * @param fel
     * @param worldState
     * @param statisticsComputer
     * @param randomizer
     * @return
     */
    boolean planificate(FutureEventList fel, WorldState worldState,
            StatisticsComputer statisticsComputer, Generator<Double> randomizer);
}
