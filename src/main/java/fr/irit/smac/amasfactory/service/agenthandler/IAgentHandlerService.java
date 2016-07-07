package fr.irit.smac.amasfactory.service.agenthandler;

import java.util.Collection;
import java.util.Map;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.service.IInfraService;

/**
 * The Interface IAgentHandlerService exposes methods to handle the agents.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
public interface IAgentHandlerService extends IInfraService {

    /**
     * Gets the agents.
     *
     * @return a collection containing the agents currently handled by the
     *         system
     */
    public Collection<IAgent> getAgents();

    /**
     * Gets the agent map.
     *
     * @return the agent map
     */
    public Map<String, IAgent> getAgentMap();

    /**
     * Gets the agent.
     *
     * @param id
     *            the id
     * @return the agent
     */
    public IAgent getAgent(String id);

    /**
     * Add an agent to the system.
     *
     * @param agent
     *            the agent to be added to the system
     */
    public void addAgent(IAgent agent);

    /**
     * Remove an agent from the system.
     *
     * @param agent
     *            the agent to be removed from the system
     */
    public void removeAgent(IAgent agent);

    /**
     * Add a collection of agents to the system.
     *
     * @param agents
     *            the agents to be added to the system
     */
    public void addAgents(Collection<IAgent> agents);

    /**
     * Remove a collection of agents from the system.
     *
     * @param agents
     *            the agents to be removed
     */
    public void removeAgents(Collection<IAgent> agents);

    /**
     * Add a agentEventListener to be notified when an agent is added.
     *
     * @param listener
     *            the listener to be added
     */
    public void addAgentEventListener(IAgentEventListener listener);

    /**
     * Removes a agentEventListener.
     *
     * @param listener
     *            the listener to be removed
     */
    public void removeAgentEventListener(IAgentEventListener listener);

    public void initAgents();
}
