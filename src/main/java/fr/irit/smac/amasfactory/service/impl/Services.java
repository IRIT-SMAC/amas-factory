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
package fr.irit.smac.amasfactory.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.impl.ShutdownRuntimeException;
import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.amasfactory.service.IServices;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;

@SuppressWarnings("rawtypes")
public class Services<A extends IAgent> implements IServices<A> {

    @JsonProperty
    private IAgentHandlerService<A> agentHandlerService;

    @JsonProperty
    private IMessagingService<IMessage> messagingService;

    @JsonProperty
    private IExecutionService<A> executionService;

    @JsonProperty
    private ILoggingService<A> loggingService;

    @JsonProperty
    private IDataSharingService<A> hazelcastService;

    public Services() {
    }

    @Override
    public IAgentHandlerService<A> getAgentHandlerService() {
        return agentHandlerService;
    }

    @Override
    public IMessagingService<IMessage> getMessagingService() {
        return messagingService;
    }

    @Override
    public IExecutionService<A> getExecutionService() {
        return executionService;
    }

    @Override
    public ILoggingService<A> getLoggingService() {
        return loggingService;
    }

    @Override
    public IDataSharingService<A> getHazelcastService() {
        return hazelcastService;
    }

    @Override
    public void start() {

        executionService.setAgentHandlerService(agentHandlerService);
        loggingService.setExecutionService(executionService);

        if (hazelcastService != null) {
            hazelcastService.setAgentHandlerService(agentHandlerService);
            hazelcastService.setExecutionService(executionService);
        }
        agentHandlerService.start();
        executionService.start();
        messagingService.start();
        loggingService.start();
        agentHandlerService.initAgents();
        agentHandlerService.getAgentMap().forEach((k, v) -> {
            v.getFeatures().getFeatureSocial().getKnowledge().setMsgBox(messagingService.getMsgBox(k));
            v.setLogger(loggingService.getAgentLogger(k));
        });

        if (hazelcastService != null) {
            hazelcastService.start();
        }
    }

    @Override
    public void stop() throws ShutdownRuntimeException {

        agentHandlerService.shutdown();
        executionService.shutdown();
        messagingService.shutdown();
        loggingService.shutdown();

        if (hazelcastService != null) {
            hazelcastService.shutdown();
        }
    }

}
