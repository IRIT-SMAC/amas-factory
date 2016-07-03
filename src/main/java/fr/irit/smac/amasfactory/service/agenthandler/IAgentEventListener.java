package fr.irit.smac.amasfactory.service.agenthandler;

import fr.irit.smac.amasfactory.agent.IAgent;

/**
 * The Interface IAgentEventListener exposes methods to be notified when events
 * about agents occurred
 *
 * @param <A>
 *            the generic type
 * @see IAgentEventEvent
 */
public interface IAgentEventListener {

    /**
     * When an agent is added.
     *
     * @param agent
     *            the agent
     */
    public void agentAdded(IAgent agent);

    /**
     * When an agent is removed.
     *
     * @param agent
     *            the agent
     */
    public void agentRemoved(IAgent agent);

}
