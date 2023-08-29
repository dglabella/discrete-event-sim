package gida.academics.labs.lab1.utils.exceptions;

public class GeneratorException extends RuntimeException {

    /**
     * 
     * @param message Descriptive message to specify what is wrong with the generator.
     */
    public GeneratorException(String message) {
        super(message);
    }

    /**
     * 
     * @param message Descriptive message to specify what is wrong with the generator.
     * @param throwable The throwable object.
     */
    public GeneratorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
