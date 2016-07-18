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
package fr.irit.smac.amasfactory.service.datasharing.impl;

import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;

/**
 * An empty implementation, for sharingless projects.
 * 
 * @author SVI
 *
 * @param <A>
 * @param <M>
 */
public class EmptySharingService<A>
    implements IDataSharingService<A> {

    @Override
    public void agentAdded(A agent) {
        // empty
    }

    @Override
    public void agentRemoved(A agent) {
        // empty
    }

    @Override
    public void start() {
        // empty
    }

    @Override
    public void shutdown() {
        // empty
    }

    @Override
    public void setAgentHandlerService(IAgentHandlerService<A> agentHandlerService) {
        // empty
    }

    @Override
    public void setExecutionService(IExecutionService<A> executionService) {
        // empty
    }

}
