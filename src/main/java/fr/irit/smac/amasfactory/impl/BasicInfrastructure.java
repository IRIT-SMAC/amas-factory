package fr.irit.smac.amasfactory.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;

/**
 * A BasicInfrastructure controls the services of the system
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public class BasicInfrastructure<A extends IInfrastructureAgent<M>, M> extends AbstractInfraService<A, M>
    implements IInfrastructure<A, M> {

    @JsonProperty
    private IAgentHandlerService<A, M> agentHandlerService;

    @JsonProperty
    private IMessagingService<M> messagingService;

    @JsonProperty
    private IExecutionService<A, M> executionService;

    @JsonProperty
    private ILoggingService<M> loggingService;

    @JsonProperty
    private IDataSharingService<A, M> hazelcastService;

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
    public IMessagingService<M> getMessagingService() {
        return this.messagingService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.IInfrastructure#getAgentHandler()
     */
    @Override
    public IAgentHandlerService<A, M> getAgentHandler() {
        return this.agentHandlerService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.IInfrastructure#getExecutionService()
     */
    @Override
    public IExecutionService<A, M> getExecutionService() {
        return this.executionService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.IInfrastructure#getDataSharingService()
     */
    @Override
    public IDataSharingService<A, M> getDataSharingService() {
        return this.hazelcastService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.amasfactory.IAgentSideInfrastructure#getLoggingService()
     */
    @Override
    public ILoggingService<M> getLoggingService() {
        return this.loggingService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.impl.AbstractInfraService#
     * setInfrastructure(fr.irit.smac.amasfactory.impl.BasicInfrastructure)
     */
    @Override
    public void setInfrastructure(BasicInfrastructure<A, M> basicInfrastructure) {
        // BasicInfrastructure should not have a nested attribute
        // "infrastructure"
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#start()
     */
    @Override
    public void start() {
        // starts each service sequencially
        
        messagingService.setInfrastructure((BasicInfrastructure<IInfrastructureAgent<M>, M>) this);
        agentHandlerService.setInfrastructure(this);
        executionService.setInfrastructure(this);
        loggingService.setInfrastructure((BasicInfrastructure<IInfrastructureAgent<M>, M>) this);

        
        this.agentHandlerService.start();
        this.executionService.start();
        this.messagingService.start();
        this.loggingService.start();
        this.agentHandlerService.setInfrastructureAgent(this);
        this.hazelcastService.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#shutdown()
     */
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
