package gida.academics.labs.lab1.model.policies;

import java.util.Optional;
import gida.academics.labs.lab1.model.Server;
import gida.academics.labs.lab1.model.Selector;
import gida.academics.labs.lab1.utils.exceptions.DequeueMalfunction;

/**
 * This class represents an implementation of a selection policy for servers that stands when there
 * is only one server to select.
 */
public final class UniqueServer implements Selector<Server> {

    private final Server server;

    public UniqueServer(Server server) {
        this.server = server;
    }

    @Override
    public Optional<Server> select() {
        if (server != null)
            return Optional.of(this.server);
        else
            throw new DequeueMalfunction("No sever must have at least one member to be selected");
    }
}
