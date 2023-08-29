package gida.academics.labs.lab1.model;

import java.util.Optional;

/**
 * This interface used to implement a selector. A selector is needed in the simulation to do some
 * selection based on a certain methodology or policy over multiple elements.
 * 
 * @author Labella, Danilo Guido
 * @since 28/04/2023
 * @version 1
 */
@FunctionalInterface
public interface Selector<T> {

    /**
     * 
     * @return An optional object with the selected candidate.
     */
    Optional<T> select();
}
