package gida.academics.labs.lab1.utils.exceptions;

/**
 * This runtime exception is supposed to be thrown when a probability density function is malformed,
 * meaning that any of its properties not stand.
 */
public class ProbabilityFunctionException extends RuntimeException {

    /**
     * 
     * @param message Descriptive message to specify what is wrong with the probability density
     *        function.
     */
    public ProbabilityFunctionException(String message) {
        super(message);
    }

    /**
     * 
     * @param message Descriptive message to specify what is wrong with the probability density
     *        function.
     * @param throwable The throwable object.
     */
    public ProbabilityFunctionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
