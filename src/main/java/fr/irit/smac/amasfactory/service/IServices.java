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
public interface IServices {

    public void start();

    public void stop() throws ShutdownRuntimeException;

    public IAgentHandlerService getAgentHandlerService();

    public IMessagingService<IMessage> getMessagingService();

    public IExecutionService getExecutionService();

    public ILoggingService getLoggingService();

    public IDataSharingService getHazelcastService();

}
