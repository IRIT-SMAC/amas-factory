/*
 * #%L
 * amas-factory
 * %%
 * Copyright (C) 2015 - 2016 IRIT - SMAC Team and Brennus Analytics
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package fr.irit.smac.amasfactory.service.agenthandler.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;

/**
 * BasicAgentHandler is a service which handles the agents.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
@SuppressWarnings("rawtypes")
public class BasicAgentHandler<A extends IAgent>
    implements IAgentHandlerService<A>, IInfraService {

    private Map<String, A> agentMap = new HashMap<>();

    private Set<IAgentEventListener<A>> listenerSet;

    /**
     * Instantiates a new basic agent handler.
     */
    public BasicAgentHandler() {
        super();
        agentMap = null;
        listenerSet = null;
    }

    /**
     * Sets the agent map.
     *
     * @param agentMap
     *            the agent map
     */
    public void setAgentMap(Map<String, A> agentMap) {
        this.agentMap = agentMap;

    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#start()
     */
    @Override
    public void start() {
        listenerSet = Collections.synchronizedSet(new HashSet<IAgentEventListener<A>>());
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#shutdown()
     */
    @Override
    public void shutdown() {
        agentMap = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * getAgents()
     */
    @Override
    public Collection<A> getAgents() {
        return agentMap.values();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * addAgent(fr.irit.smac.amasfactory.agent.IInfrastructureAgent)
     */
    @Override
    public void addAgent(A agent) {
        agentMap.put(agent.getFeatures().getFeatureBasic().getKnowledge().getId(), agent);
        notifyAgentAdded(agent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * removeAgent(fr.irit.smac.amasfactory.agent.IInfrastructureAgent)
     */
    @Override
    public void removeAgent(A agent) {
        agentMap.remove(agent);
        notifyAgentRemoved(agent);
    }

    /**
     * Notify when an agent is added.
     *
     * @param agent
     *            the agent
     */
    private void notifyAgentAdded(A agent) {
        for (IAgentEventListener<A> listener : listenerSet) {
            listener.agentAdded(agent);
        }
    }

    /**
     * Notify when an agent is removed.
     *
     * @param agent
     *            the agent
     */
    private void notifyAgentRemoved(A agent) {
        for (IAgentEventListener<A> listener : listenerSet) {
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
    public void addAgents(Collection<A> agents) {
        for (A a : agents) {
            addAgent(a);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * removeAgents(java.util.Collection)
     */
    @Override
    public void removeAgents(Collection<A> agents) {
        for (A a : agents) {
            removeAgent(a);
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
    public void addAgentEventListener(IAgentEventListener<A> listener) {
        listenerSet.add(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * removeAgentEventListener(fr.irit.smac.amasfactory.service.agenthandler.
     * IAgentEventListener)
     */
    @Override
    public void removeAgentEventListener(IAgentEventListener<A> listener) {
        listenerSet.add(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * getAgentMap()
     */
    @Override
    public Map<String, A> getAgentMap() {
        return Collections.unmodifiableMap(agentMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService#
     * getAgent(java.lang.String)
     */
    @Override
    public A getAgent(String id) {
        return agentMap.get(id);
    }

    @Override
    public void initAgents() {

        getAgentMap().forEach((k, v) -> {

            notifyAgentAdded(v);
        });
    }

}
