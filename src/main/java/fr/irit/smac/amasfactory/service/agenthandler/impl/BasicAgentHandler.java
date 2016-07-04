package fr.irit.smac.amasfactory.service.agenthandler.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.irit.smac.amasfactory.agent.EExtraKnowledgeSkill;
import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.agent.social.IExtraKnowledgeSocial;
import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;

/**
 * BasicAgentHandler is a service which handles the agents.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
public class BasicAgentHandler
    implements IAgentHandlerService, IInfraService {

    private Map<String, IAgent> agentMap = new HashMap<>();

    private Set<IAgentEventListener> listenerSet;

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
     * @param agentMap
     *            the agent map
     */
    public void setAgentMap(Map<String, IAgent> agentMap) {
        this.agentMap = agentMap;

    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#start()
     */
    @Override
    public void start() {
        this.listenerSet = Collections.synchronizedSet(new HashSet<IAgentEventListener>());
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#shutdown()
     */
    @Override
    public void shutdown() {
        this.agentMap = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * getAgents()
     */
    @Override
    public Collection<IAgent> getAgents() {
        return this.agentMap.values();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * addAgent(fr.irit.smac.amasfactory.agent.IInfrastructureAgent)
     */
    @Override
    public void addAgent(IAgent agent) {
        this.agentMap.put(agent.getId(), agent);
        this.notifyAgentAdded(agent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * removeAgent(fr.irit.smac.amasfactory.agent.IInfrastructureAgent)
     */
    @Override
    public void removeAgent(IAgent agent) {
        this.agentMap.remove(agent);
        this.notifyAgentRemoved(agent);
    }

    /**
     * Notify when an agent is added.
     *
     * @param agent
     *            the agent
     */
    private void notifyAgentAdded(IAgent agent) {
        for (IAgentEventListener listener : this.listenerSet) {
            listener.agentAdded(agent);
        }
    }

    /**
     * Notify when an agent is removed.
     *
     * @param agent
     *            the agent
     */
    private void notifyAgentRemoved(IAgent agent) {
        for (IAgentEventListener listener : this.listenerSet) {
            listener.agentRemoved(agent);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * addAgents(java.util.Collection)
     */
    @Override
    public void addAgents(Collection<IAgent> agents) {
        for (IAgent a : agents) {
            this.addAgent(a);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * removeAgents(java.util.Collection)
     */
    @Override
    public void removeAgents(Collection<IAgent> agents) {
        for (IAgent a : agents) {
            this.removeAgent(a);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * addAgentEventListener(fr.irit.smac.amasfactory.service.agenthandler.
     * IAgentEventListener)
     */
    @Override
    public void addAgentEventListener(IAgentEventListener listener) {
        this.listenerSet.add(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * removeAgentEventListener(fr.irit.smac.amasfactory.service.agenthandler.
     * IAgentEventListener)
     */
    @Override
    public void removeAgentEventListener(IAgentEventListener listener) {
        this.listenerSet.add(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * getAgentMap()
     */
    @Override
    public Map<String, IAgent> getAgentMap() {
        return Collections.unmodifiableMap(this.agentMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * getAgent(java.lang.String)
     */
    @Override
    public IAgent getAgent(String id) {
        return this.agentMap.get(id);
    }

    @Override
    public void initAgents(IMessagingService<IMessage> messagingService, ILoggingService loggingService) {

        
        this.getAgentMap().forEach((k, v) -> {

            ((IExtraKnowledgeSocial) v.getKnowledge().getExtraKnowledge().get(EExtraKnowledgeSkill.SOCIAL.getName())).setMsgBox(messagingService.getMsgBox(k));
            v.getSkill().setKnowledge(v.getKnowledge());
            v.setId(k);
            v.setLogger(loggingService.getAgentLogger(k));
            
            this.notifyAgentAdded(v);
        });
    }

}
