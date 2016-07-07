package fr.irit.smac.amasfactory;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.impl.ShutdownRuntimeException;
import fr.irit.smac.amasfactory.service.IServices;

/**
 * The Interface IInfrastructure exposes methods to get some services.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IInfrastructure<T extends IServices> {

    /**
     * Starts the services.
     */
    public void start();

    /**
     * Shutdown the services.
     *
     * @throws ShutdownRuntimeException
     *             the shutdown runtime exception
     */
    public void shutdown() throws ShutdownRuntimeException;

    public T getServices();

}
