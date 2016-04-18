package fr.irit.smac.amasfactory.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;

public class BasicInfrastructure<A extends IInfrastructureAgent<M>, M> extends AbstractInfraService<A, M>
    implements IInfrastructure<A, M> {

    private IMessagingService<M>       messagingService;
    private IAgentHandlerService<A, M> agentHandlerService;
    private IExecutionService<A, M>    executionService;
    private ILoggingService<M>         loggingService;
    private IDataSharingService<A, M>  hazelcastService;

    public BasicInfrastructure(
        @JsonProperty(value = "messagingService", required = true) IMessagingService<M> messagingService,
        @JsonProperty(value = "agentHandlerService", required = true) IAgentHandlerService<A, M> agentHandlerService,
        @JsonProperty(value = "executionService", required = true) IExecutionService<A, M> executionService,
        @JsonProperty(value = "loggingService", required = true) ILoggingService<M> loggingService,
        @JsonProperty(value = "hazelcastService", required = true) IDataSharingService<A, M> hazelcastService) {
        super();

        // not working because type is Interface
        this.messagingService = messagingService;
        this.agentHandlerService = agentHandlerService;
        this.executionService = executionService;
        this.loggingService = loggingService;
        this.hazelcastService = hazelcastService;

        messagingService.setInfrastructure((BasicInfrastructure<IInfrastructureAgent<M>, M>) this);
        agentHandlerService.setInfrastructure(this);
        executionService.setInfrastructure(this);
        loggingService.setInfrastructure((BasicInfrastructure<IInfrastructureAgent<M>, M>) this);
        hazelcastService.setInfrastructure(this);
        
        this.start();
    }

    @Override
    public IMessagingService<M> getMessagingService() {
        return this.messagingService;
    }

    @Override
    public IAgentHandlerService<A, M> getAgentHandler() {
        return this.agentHandlerService;
    }

    @Override
    public IExecutionService<A, M> getExecutionService() {
        return this.executionService;
    }

    @Override
    public IDataSharingService<A, M> getDataSharingService() {
        return this.hazelcastService;
    }

    @Override
    public void setInfrastructure(BasicInfrastructure<A, M> basicInfrastructure) {

    }

    @Override
    public void start() {
        // starts each service sequencially
        this.agentHandlerService.start();
        this.executionService.start();
        this.messagingService.start();
        this.loggingService.start();
        this.agentHandlerService.setInfrastructureAgent(this);
        this.hazelcastService.start();
    }

    @Override
    public void shutdown() {
        // shutdown each service sequencially
        this.agentHandlerService.shutdown();
        this.executionService.shutdown();
        this.messagingService.shutdown();
        this.loggingService.shutdown();
        this.hazelcastService.shutdown();
    }

    @Override
    public ILoggingService<M> getLoggingService() {
        return this.loggingService;
    }

}
