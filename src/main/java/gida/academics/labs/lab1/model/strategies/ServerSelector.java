package gida.academics.labs.lab1.model.strategies;

import java.util.List;
import java.util.Optional;
import gida.academics.labs.lab1.model.Server;

/**
 * This interface used to implement a selector. A selector is needed in the simulation to do some
 * selection based on a certain methodology or policy over multiple elements.
 * 
 * @author Labella, Danilo Guido
 * @since 28/04/2023
 * @version 1
 */
@FunctionalInterface
public interface ServerSelector {

    Server selectServer(List<Server> servers);
}
