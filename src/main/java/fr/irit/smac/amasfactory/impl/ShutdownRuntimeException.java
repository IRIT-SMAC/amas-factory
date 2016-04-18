package fr.irit.smac.amasfactory.impl;

/**
 * A ShutdownRuntimeException is thrown when an exception occured during the
 * shutdown of a service
 */
@SuppressWarnings("serial")
public class ShutdownRuntimeException extends Exception {

    /**
     * Instantiates a new shutdown runtime exception.
     *
     * @param message
     *            the message
     */
    public ShutdownRuntimeException(String message) {
        super(message);
    }
}
