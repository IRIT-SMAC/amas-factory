package fr.irit.smac.amasfactory.service.agenthandler;

import java.util.Collection;
import java.util.Map;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;


public interface IAgentHandlerService<A extends IInfrastructureAgent<M>,M> extends IInfraService<A,M>
{
    /**
     * 
     * @return a collection containing the agents currently handled by the
     *         system
     */
    Collection<A> getAgents();
    
    Map<String,A> getAgentMap();
    
     A getAgent(String id);


    /**
     * Add an agent to the system
     * 
     * @param agent
     *            the agent to be added to the system
     */
    void addAgent(A agent);

    /**
     * Remove an agent from the system
     * 
     * @param agent
     *            the agent to be removed from the system
     */
    void removeAgent(A agent);

    /**
     * Add a collection of agents to the system
     * 
     * @param agents
     *            the agents to be added to the system
     */
    void addAgents(Collection<A> agents);

    /**
     * Remove a collection of agents from the system
     * 
     * @param agents
     *            the agents to be removed
     */
    void removeAgents(Collection<A> agents);
    
    
    /**
     * Add a agentEventListener to be notified when an agent is added
     * @param listener the listener to be added
     */
    void addAgentEventListener(IAgentEventListener<A> listener);
    
    
    /**
     * Removes a agentEventListener
     * @param listener the listener to be removed
     */
    void removeAgentEventListener(IAgentEventListener<A> listener);
    
}
