package gida.academics.labs.lab1.bootstrapping;

import gida.academics.labs.lab1.model.Event;
import gida.academics.labs.lab1.utils.generators.Generator;

public interface Planificable {

    /**
     * Get the event associated to this planificable.;
     * 
     * @return The event.
     */
    Event getEvent();

    /**
     * 
     * @param currentEvent The current event, which is the one that this planificator will be
     *        processing (i.e the head removed from future events list).
     * @param fel The future event list. Used to insert future events generated by this
     *        planificator.
     * @param randomizer The random number generator. Used to generates new clock values for the
     *        future events.
     */
    void planificate(FutureEventList fel, Generator<Double> randomizer);
}