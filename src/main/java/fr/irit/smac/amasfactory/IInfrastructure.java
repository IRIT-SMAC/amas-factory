package fr.irit.smac.amasfactory;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;

/**
 * The Interface IInfrastructure exposes methods to get some services.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IInfrastructure<A extends IAgent<M>, M>
    extends IAgentSideInfrastructure<M>, IInfraService<A, M> {

    /**
     * Gets the agent handler.
     *
     * @return the agent handler
     */
    public IAgentHandlerService<A, M> getAgentHandler();

    /**
     * Gets the execution service.
     *
     * @return the execution service
     */
    public IExecutionService<A, M> getExecutionService();

    /**
     * Gets the data sharing service.
     *
     * @return the data sharing service
     */
    public IDataSharingService<A, M> getDataSharingService();

}
