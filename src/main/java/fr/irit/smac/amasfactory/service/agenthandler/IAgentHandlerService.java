package fr.irit.smac.amasfactory.service.agenthandler;

import java.util.Collection;
import java.util.Map;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.impl.BasicInfrastructure;
import fr.irit.smac.amasfactory.service.IInfraService;

/**
 * The Interface IAgentHandlerService exposes methods to handle the agents.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
public interface IAgentHandlerService<A extends IAgent<M>, M> extends IInfraService<A, M> {

    /**
     * Gets the agents.
     *
     * @return a collection containing the agents currently handled by the
     *         system
     */
    Collection<A> getAgents();

    /**
     * Gets the agent map.
     *
     * @return the agent map
     */
    Map<String, A> getAgentMap();

    /**
     * Gets the agent.
     *
     * @param id
     *            the id
     * @return the agent
     */
    A getAgent(String id);

    /**
     * Add an agent to the system.
     *
     * @param agent
     *            the agent to be added to the system
     */
    void addAgent(A agent);

    /**
     * Remove an agent from the system.
     *
     * @param agent
     *            the agent to be removed from the system
     */
    void removeAgent(A agent);

    /**
     * Add a collection of agents to the system.
     *
     * @param agents
     *            the agents to be added to the system
     */
    void addAgents(Collection<A> agents);

    /**
     * Remove a collection of agents from the system.
     *
     * @param agents
     *            the agents to be removed
     */
    void removeAgents(Collection<A> agents);

    /**
     * Add a agentEventListener to be notified when an agent is added.
     *
     * @param listener
     *            the listener to be added
     */
    void addAgentEventListener(IAgentEventListener<A> listener);

    /**
     * Removes a agentEventListener.
     *
     * @param listener
     *            the listener to be removed
     */
    void removeAgentEventListener(IAgentEventListener<A> listener);

    /**
     * Sets the infrastructure agent.
     *
     * @param basicInfrastructure
     *            the basic infrastructure
     */
    void setInfrastructureAgent(BasicInfrastructure<A, M> basicInfrastructure);

}
