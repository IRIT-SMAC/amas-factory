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

/**
 * The Interface IAgentEventListener exposes methods to be notified when events
 * about agents occurred
 *
 * @param <A>
 *            the type of agent
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
