package gida.academics.labs.lab1.utils.generators;

/**
 * This interface used to specify any generator needed in the simulator, such as a number generator
 * for entities id, servers id, queues id; or randoms numbers, randoms string, etc.
 * 
 * @author Labella, Danilo Guido
 * @since 28/04/2023
 * @version 1
 */
@FunctionalInterface
public interface Generator<T> {

    /**
     * Generates a
     * 
     * @return
     */
    T generate();
}
