package gida.academics.labs.lab1.utils.exceptions;

/**
 * This runtime exception is supposed to be thrown when dequeuing an entity is posible but actually
 * there is none, showing an inconsistency in the simulation run.
 * 
 */
public class DequeueMalfunction extends RuntimeException {

    /**
     * 
     * @param message Descriptive message to specify what's wrong with the dequeuing process.
     */
    public DequeueMalfunction(String message) {
        super(message);
    }

    /**
     * 
     * @param message Descriptive message to specify what's wrong with the dequeuing process.
     * 
     * @param throwable The throwable object.
     */
    public DequeueMalfunction(String message, Throwable throwable) {
        super(message, throwable);
    }
}
