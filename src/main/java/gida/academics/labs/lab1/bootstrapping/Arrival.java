package gida.academics.labs.lab1.bootstrapping;

import java.util.Optional;
import javax.management.RuntimeErrorException;
import gida.academics.labs.lab1.model.Entity;
import gida.academics.labs.lab1.model.Event;
import gida.academics.labs.lab1.model.Selector;
import gida.academics.labs.lab1.model.Server;
import gida.academics.labs.lab1.model.WorldState;
import gida.academics.labs.lab1.model.engine.StatisticsComputer;
import gida.academics.labs.lab1.utils.generators.Generator;

public class Arrival implements Event {

    private final double clock;
    private final Entity entity;
    private final int priority;
    private final Selector<Server> serverSelectionPolicy;

    public Arrival(double clock, Entity entity, Selector<Server> serverSelectionPolicy) {
        this.clock = clock;
        this.entity = entity;
        this.priority = 0;
        this.serverSelectionPolicy = serverSelectionPolicy;

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
            StatisticsComputer statisticsComputer, Generator<Double> randomizer) {
        boolean ret = false;
        if (worldState.thereIsAnAvailableServer()) {

            Optional<Server> optional = serverSelectionPolicy.select();
            if (optional.isPresent()) {
                optional.get().setCurrentEntity(this.getEntity());
                this.getEntity().setCurrentServer(optional.get());
            } else {
                // throw new RuntimeErrorException(
                // "world state says that there is an availabel server, but the server selection
                // policy is not returning it");
                ret = false;
            }

        } else {

        }

        Arrival nextArrival =
                new Arrival(clock + randomizer.generate(), entity, this.serverSelectionPolicy);

        fel.insert(nextArrival);

        return ret;
    }
}
