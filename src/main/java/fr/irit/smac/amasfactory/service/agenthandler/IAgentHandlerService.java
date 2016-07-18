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
package fr.irit.smac.amasfactory.service.agenthandler;

import java.util.Collection;
import java.util.Map;

import fr.irit.smac.amasfactory.service.IService;

/**
 * The Interface IAgentHandlerService exposes methods to handle the agents.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
public interface IAgentHandlerService<A> extends IService {

    /**
     * Gets the agents.
     *
     * @return a collection containing the agents currently handled by the
     *         system
     */
    public Collection<A> getAgents();

    /**
     * Gets the agent map.
     *
     * @return the agent map
     */
    public Map<String, A> getAgentMap();

    /**
     * Gets the agent.
     *
     * @param id
     *            the id
     * @return the agent
     */
    public A getAgent(String id);

    /**
     * Add an agent to the system.
     *
     * @param agent
     *            the agent to be added to the system
     */
    public void addAgent(A agent);

    /**
     * Remove an agent from the system.
     *
     * @param agent
     *            the agent to be removed from the system
     */
    public void removeAgent(A agent);

    /**
     * Add a collection of agents to the system.
     *
     * @param agents
     *            the agents to be added to the system
     */
    public void addAgents(Collection<A> agents);

    /**
     * Remove a collection of agents from the system.
     *
     * @param agents
     *            the agents to be removed
     */
    public void removeAgents(Collection<A> agents);

    /**
     * Add a agentEventListener to be notified when an agent is added.
     *
     * @param listener
     *            the listener to be added
     */
    public void addAgentEventListener(IAgentEventListener<A> listener);

    /**
     * Removes a agentEventListener.
     *
     * @param listener
     *            the listener to be removed
     */
    public void removeAgentEventListener(IAgentEventListener<A> listener);

    public void initAgents();
}
