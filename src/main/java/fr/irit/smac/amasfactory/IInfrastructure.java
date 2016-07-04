package fr.irit.smac.amasfactory;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.agent.features.IFeatures;
import fr.irit.smac.amasfactory.impl.ShutdownRuntimeException;
import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;

/**
 * The Interface IInfrastructure exposes methods to get some services.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IInfrastructure {

    /**
     * Gets the agent handler.
     *
     * @return the agent handler
     */
    public IAgentHandlerService getAgentHandler();

    /**
     * Gets the execution service.
     *
     * @return the execution service
     */
    public IExecutionService getExecutionService();

    /**
     * Gets the data sharing service.
     *
     * @return the data sharing service
     */
    public IDataSharingService getDataSharingService();

    /**
     * Gets the messaging service.
     *
     * @return the messaging service
     */
    public IMessagingService<IMessage> getMessagingService();

    /**
     * Gets the logging service.
     *
     * @return the logging service
     */
    public ILoggingService getLoggingService();

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

}
