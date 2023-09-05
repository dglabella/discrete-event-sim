package gida.academics.labs.lab1.utils.exceptions;

/**
 * 
 */
public class EmptyServerList extends RuntimeException {
    /**
     * 
     * @param message Descriptive message to specify what's wrong with the dequeuing process.
     */
    public EmptyServerList(String message) {
        super(message);
    }

    /**
     * 
     * @param message Descriptive message to specify what's wrong with the dequeuing process.
     * 
     * @param throwable The throwable object.
     */
    public EmptyServerList(String message, Throwable throwable) {
        super(message, throwable);
    }
}
