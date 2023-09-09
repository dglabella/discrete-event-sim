package gida.academics.labs.lab1.model;

import java.util.List;
import java.util.Optional;
import gida.academics.labs.lab1.utils.exceptions.EmptyServerList;

/**
 * 
 */
public class WorldState {

    public final List<Server> servers;

    /**
     * 
     * @param servers
     */
    public WorldState(List<Server> servers) {
        this.servers = servers;
        if (this.servers.size() == 0)
            throw new EmptyServerList("cannot instantiate a world state without servers");
    }

    /**
     * 
     * @return
     */
    public List<Server> getServers() {
        return this.servers;
    }

    public boolean isThereAServerAvailable() {
        boolean ret = false;
        for (Server server : this.servers)
            ret = ret || !server.isBusy();

        return ret;
    }
}
