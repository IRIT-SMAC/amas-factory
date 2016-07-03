package fr.irit.smac.amasfactory.service.logging;

import org.slf4j.Logger;

import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;

/**
 * The Interface ILoggingService exposes methods to use the logger service.
 *
 * @param <M> the generic type
 */
public interface ILoggingService extends IInfraService {
    
    /**
     * Gets the standard logger.
     *
     * @param loggerName the logger name
     * @return the standard logger
     */
    public Logger getStandardLogger(String loggerName);

    /**
     * Gets the agent logger.
     *
     * @param loggerName the logger name
     * @return the agent logger
     */
    public Logger getAgentLogger(String loggerName);


    public void setExecutionService(IExecutionService executionService);

}
