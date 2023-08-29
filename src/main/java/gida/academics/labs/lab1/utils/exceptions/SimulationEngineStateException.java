package gida.academics.labs.lab1.utils.exceptions;

/**
 * This runtime exception is supposed to be thrown in a simulation engine implementation.
 * 
 */
public class SimulationEngineStateException extends RuntimeException {

    /**
     * 
     * @param message Descriptive message to specify what is wrong with the simulation engine.
     */
    public SimulationEngineStateException(String message) {
        super(message);
    }

    /**
     * 
     * @param message Descriptive message to specify what is wrong with the simulation engine.
     * @param throwable The throwable object.
     */
    public SimulationEngineStateException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
