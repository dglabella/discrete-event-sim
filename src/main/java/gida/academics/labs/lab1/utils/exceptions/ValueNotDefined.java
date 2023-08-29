package gida.academics.labs.lab1.utils.exceptions;

public class ValueNotDefined extends RuntimeException {

    /**
     * 
     * @param message message to specify that a given value is not in the domain.
     */
    public ValueNotDefined(String message) {
        super(message);
    }

    /**
     * 
     * @param message Descriptive message to specify what is wrong with the simulation engine.
     * @param throwable The throwable object.
     */
    public ValueNotDefined(String message, Throwable throwable) {
        super(message, throwable);
    }
}
