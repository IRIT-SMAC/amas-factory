package fr.irit.smac.amasfactory;

import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;

/**
 * The Interface IAgentSideInfrastructure.
 *
 * @param <M>
 *            the generic type
 */
public interface IAgentSideInfrastructure<M> {

    /**
     * Gets the messaging service.
     *
     * @return the messaging service
     */
    public IMessagingService<M> getMessagingService();

    /**
     * Gets the logging service.
     *
     * @return the logging service
     */
    public ILoggingService<M> getLoggingService();
}
