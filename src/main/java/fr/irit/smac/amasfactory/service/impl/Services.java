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

    public Services(
        @JsonProperty(value = "messagingService", required = true) IMessagingService<IMessage> messagingService,
        @JsonProperty(value = "agentHandlerService", required = true) IAgentHandlerService<A> agentHandlerService,
        @JsonProperty(value = "executionService", required = true) IExecutionService<A> executionService,
        @JsonProperty(value = "loggingService", required = true) ILoggingService<A> loggingService,
        @JsonProperty(value = "hazelcastService", required = true) IDataSharingService<A> hazelcastService) {
        super();

        this.messagingService = messagingService;
        this.agentHandlerService = agentHandlerService;
        this.executionService = executionService;
        this.loggingService = loggingService;
        this.hazelcastService = hazelcastService;
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

        executionService.setAgentHandlerService(this.agentHandlerService);
        loggingService.setExecutionService(this.executionService);
        hazelcastService.setAgentHandlerService(this.agentHandlerService);
        hazelcastService.setExecutionService(this.executionService);

        this.agentHandlerService.start();
        this.executionService.start();
        this.messagingService.start();
        this.loggingService.start();
        this.agentHandlerService.initAgents();
        this.agentHandlerService.getAgentMap().forEach((k, v) -> {
            v.getFeatures().getFeatureSocial().getKnowledge().setMsgBox(messagingService.getMsgBox(k));
            v.setLogger(loggingService.getAgentLogger(k));
        });
        this.hazelcastService.start();
    }

    @Override
    public void stop() throws ShutdownRuntimeException {

        this.agentHandlerService.shutdown();
        this.executionService.shutdown();
        this.messagingService.shutdown();
        this.loggingService.shutdown();
        this.hazelcastService.shutdown();
    }

}
