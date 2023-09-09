package gida.academics.labs.lab1.bootstrapping;

import java.util.List;
import java.util.Optional;
import gida.academics.labs.lab1.model.Channel;
import gida.academics.labs.lab1.model.Entity;
import gida.academics.labs.lab1.model.Event;
import gida.academics.labs.lab1.model.Server;
import gida.academics.labs.lab1.model.WorldState;
import gida.academics.labs.lab1.model.engine.StatisticsComputer;
import gida.academics.labs.lab1.model.strategies.ChannelSelector;
import gida.academics.labs.lab1.model.strategies.ServerSelector;
import gida.academics.labs.lab1.utils.generators.Generator;

public class Arrival implements Event, ServerSelector, ChannelSelector {

    private final double clock;
    private final Entity entity;
    private final int priority;

    public Arrival(double clock, Entity entity) {
        this.clock = clock;
        this.entity = entity;
        this.priority = 0;
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

        if (worldState.isThereAServerAvailable()) {
            Server server = worldState.selectServer(worldState.getServers());
            server.setCurrentEntity(this.getEntity());
            this.getEntity().setCurrentServer(server);
        } else {
            worldState.enqueue(this.getEntity());
        }

        Arrival nextArrival = new Arrival(clock + randomizer.generate(), entity);

        fel.insert(nextArrival);

        return ret;
    }

    @Override
    public Channel selectChannel(List<Channel> channels) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectChannel'");
    }

    @Override
    public Server selectServer(List<Server> servers) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectServer'");
    }
}
