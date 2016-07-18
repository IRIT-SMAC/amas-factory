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
package fr.irit.smac.amasfactory.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.impl.ShutdownRuntimeException;
import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IServices<A> {

    public void start();

    public void stop() throws ShutdownRuntimeException;

    public IAgentHandlerService<A> getAgentHandlerService();

    public IMessagingService<IMessage> getMessagingService();

    public IExecutionService<A> getExecutionService();

    public ILoggingService<A> getLoggingService();

    public IDataSharingService<A> getHazelcastService();

    public void setAgentHandlerService(IAgentHandlerService<A> agentHandlerService);

    public void setMessagingService(IMessagingService<IMessage> messagingService);

    public void setExecutionService(IExecutionService<A> executionService);

    public void setLoggingService(ILoggingService<A> loggingService);

    public void setHazelcastService(IDataSharingService<A> hazelcastService);

}
