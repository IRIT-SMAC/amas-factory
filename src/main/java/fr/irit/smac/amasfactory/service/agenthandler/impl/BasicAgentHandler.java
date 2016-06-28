package fr.irit.smac.amasfactory.service.agenthandler.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.impl.BasicInfrastructure;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;

/**
 * BasicAgentHandler is a service which handles the agents.
 *
 * @param <A> the generic type
 * @param <M> the generic type
 */
public class BasicAgentHandler<A extends IAgent<M>, M> extends AbstractInfraService<A, M>
    implements IAgentHandlerService<A, M> {

    private Map<String, A>              agentMap = new HashMap<>();
    
    private Set<IAgentEventListener<A>> listenerSet;

    /**
     * Instantiates a new basic agent handler.
     */
    public BasicAgentHandler() {
        super();
        this.agentMap = null;
        this.listenerSet = null;
    }

    /**
     * Sets the agent map.
     *
     * @param agentMap the agent map
     */
    public void setAgentMap(Map<String, A> agentMap) {
        this.agentMap = agentMap;

    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.impl.AbstractInfraService#setInfrastructure(fr.irit.smac.amasfactory.impl.BasicInfrastructure)
     */
    @Override
    public void setInfrastructure(BasicInfrastructure<A, M> basicInfrastructure) {

        this.infrastructure = basicInfrastructure;
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#setInfrastructureAgent(fr.irit.smac.amasfactory.impl.BasicInfrastructure)
     */
    @Override
    public void setInfrastructureAgent(BasicInfrastructure<A, M> basicInfrastructure) {
        for (Map.Entry<String, A> entry : agentMap.entrySet()) {
            String key = entry.getKey();
            A value = (A) entry.getValue();
            value.init(infrastructure, key);
            this.notifyAgentAdded(value);

        }
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.IInfraService#start()
     */
    @Override
    public void start() {
        this.listenerSet = Collections.synchronizedSet(new HashSet<IAgentEventListener<A>>());
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.IInfraService#shutdown()
     */
    @Override
    public void shutdown() {
        this.agentMap = null;
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#getAgents()
     */
    @Override
    public Collection<A> getAgents() {
        return this.agentMap.values();
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#addAgent(fr.irit.smac.amasfactory.agent.IInfrastructureAgent)
     */
    @Override
    public void addAgent(A agent) {
        this.agentMap.put(agent.getId(), agent);
        this.notifyAgentAdded(agent);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#removeAgent(fr.irit.smac.amasfactory.agent.IInfrastructureAgent)
     */
    @Override
    public void removeAgent(A agent) {
        this.agentMap.remove(agent);
        this.notifyAgentRemoved(agent);
    }

    /**
     * Notify when an agent is added.
     *
     * @param agent the agent
     */
    private void notifyAgentAdded(A agent) {
        for (IAgentEventListener<A> listener : this.listenerSet) {
            listener.agentAdded(agent);
        }
    }

    /**
     * Notify when an agent is removed.
     *
     * @param agent the agent
     */
    private void notifyAgentRemoved(A agent) {
        for (IAgentEventListener<A> listener : this.listenerSet) {
            listener.agentRemoved(agent);
        }
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#addAgents(java.util.Collection)
     */
    @Override
    public void addAgents(Collection<A> agents) {
        for (A a : agents) {
            this.addAgent(a);
        }
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#removeAgents(java.util.Collection)
     */
    @Override
    public void removeAgents(Collection<A> agents) {
        for (A a : agents) {
            this.removeAgent(a);
        }

    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#addAgentEventListener(fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener)
     */
    @Override
    public void addAgentEventListener(IAgentEventListener<A> listener) {
        this.listenerSet.add(listener);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#removeAgentEventListener(fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener)
     */
    @Override
    public void removeAgentEventListener(IAgentEventListener<A> listener) {
        this.listenerSet.add(listener);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#getAgentMap()
     */
    @Override
    public Map<String, A> getAgentMap() {
        return Collections.unmodifiableMap(this.agentMap);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#getAgent(java.lang.String)
     */
    @Override
    public A getAgent(String id) {
        return this.agentMap.get(id);
    }

}
