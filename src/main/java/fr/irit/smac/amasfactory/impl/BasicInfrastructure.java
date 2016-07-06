package fr.irit.smac.amasfactory.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;

/**
 * A BasicInfrastructure controls the services of the system
 *
 * @param <A>
 *            the generic type
 * @param the
 *            generic type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public class BasicInfrastructure
    implements IInfrastructure {

    @JsonProperty
    private IAgentHandlerService agentHandlerService;

    @JsonProperty
    private IMessagingService<IMessage> messagingService;

    @JsonProperty
    private IExecutionService executionService;

    @JsonProperty
    private ILoggingService loggingService;

    @JsonProperty
    private IDataSharingService hazelcastService;

    /**
     * Instantiates a new basic infrastructure.
     *
     * @param messagingService
     *            the messaging service
     * @param agentHandlerService
     *            the agent handler service
     * @param executionService
     *            the execution service
     * @param loggingService
     *            the logging service
     * @param hazelcastService
     *            the hazelcast service
     */
    public BasicInfrastructure(
        @JsonProperty(value = "messagingService", required = true) IMessagingService<IMessage> messagingService,
        @JsonProperty(value = "agentHandlerService", required = true) IAgentHandlerService agentHandlerService,
        @JsonProperty(value = "executionService", required = true) IExecutionService executionService,
        @JsonProperty(value = "loggingService", required = true) ILoggingService loggingService,
        @JsonProperty(value = "hazelcastService", required = true) IDataSharingService hazelcastService) {
        super();

        this.messagingService = messagingService;
        this.agentHandlerService = agentHandlerService;
        this.executionService = executionService;
        this.loggingService = loggingService;
        this.hazelcastService = hazelcastService;

        this.start();
    }

    public BasicInfrastructure() {
        this.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.amasfactory.IAgentSideInfrastructure#getMessagingService()
     */
    @Override
    public IMessagingService<IMessage> getMessagingService() {
        return this.messagingService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.IInfrastructure#getAgentHandler()
     */
    @Override
    public IAgentHandlerService getAgentHandler() {
        return this.agentHandlerService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.IInfrastructure#getExecutionService()
     */
    @Override
    public IExecutionService getExecutionService() {
        return this.executionService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.IInfrastructure#getDataSharingService()
     */
    @Override
    public IDataSharingService getDataSharingService() {
        return this.hazelcastService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.amasfactory.IAgentSideInfrastructure#getLoggingService()
     */
    @Override
    public ILoggingService getLoggingService() {
        return this.loggingService;
    }

    @Override
    public void start() {
        // starts each service sequencially

        executionService.setAgentHandlerService(this.agentHandlerService);
        loggingService.setExecutionService(this.executionService);
        hazelcastService.setAgentHandlerService(this.agentHandlerService);
        hazelcastService.setExecutionService(this.executionService);

        this.agentHandlerService.start();
        this.executionService.start();
        this.messagingService.start();
        this.loggingService.start();
        this.agentHandlerService.initAgents(this.messagingService, this.loggingService);
        this.hazelcastService.start();
        
    }

    @Override
    public void shutdown() throws ShutdownRuntimeException {
        // shutdown each service sequencially
        this.agentHandlerService.shutdown();
        this.executionService.shutdown();
        this.messagingService.shutdown();
        this.loggingService.shutdown();
        this.hazelcastService.shutdown();
    }

}
