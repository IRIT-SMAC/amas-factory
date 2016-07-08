package fr.irit.smac.amasfactory.service.agenthandler;

/**
 * The Interface IAgentEventListener exposes methods to be notified when events
 * about agents occurred
 *
 * @param <A>
 *            the generic type
 * @see IAgentEventEvent
 */
public interface IAgentEventListener<A> {

    /**
     * When an agent is added.
     *
     * @param agent
     *            the agent
     */
    public void agentAdded(A agent);

    /**
     * When an agent is removed.
     *
     * @param agent
     *            the agent
     */
    public void agentRemoved(A agent);

}
