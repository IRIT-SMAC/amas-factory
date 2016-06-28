package fr.irit.smac.amasfactory.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.impl.BasicInfrastructure;
import fr.irit.smac.amasfactory.impl.ShutdownRuntimeException;

/**
 * The Interface IInfraService exposes methods to control a service.
 *
 * @param <A> the generic type
 * @param <M> the generic type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IInfraService<A extends IAgent<M>, M> {

    /**
     * Start the service.
     */
    public void start();

    /**
     * Shutdown the service.
     *
     * @throws ShutdownRuntimeException the shutdown runtime exception
     */
    public void shutdown() throws ShutdownRuntimeException;

    /**
     * Sets the infrastructure.
     *
     * @param basicInfrastructure the basic infrastructure
     */
    public void setInfrastructure(BasicInfrastructure<A, M> basicInfrastructure);

}
