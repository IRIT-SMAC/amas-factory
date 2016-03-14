package fr.irit.smac.amasfactory.impl;

import java.util.Map;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;

public class BasicInfrastructure<A extends IInfrastructureAgent<M>, M> extends AbstractInfraService<A, M> implements IInfrastructure<A, M> 
{
    private IMessagingService<M> messagingService;
    private IAgentHandlerService<A,M> agentHandler;
    private IExecutionService<A,M> executionService;
    private ILoggingService<M> loggingService;
    
    
    public BasicInfrastructure()
    {
        super();
        this.messagingService = null;
        this.agentHandler = null;
        this.executionService = null;
    }

    @Override
    public IMessagingService<M> getMessagingService()
    {
        return this.messagingService;
    }

    @Override
    public IAgentHandlerService<A,M> getAgentHandler()
    {
        return this.agentHandler;
    }

    @Override
    public IExecutionService<A,M> getExecutionService()
    {
        return this.executionService;
    }

    @Override
    public void init(IInfrastructure<A, M> infrastructure, Map<String, Object> parameters)
    {
        if (infrastructure != null) {
            throw new IllegalArgumentException("An instance of infrastructure already exists");
        }
        super.init(this, parameters);
    }

    @Override
    public void start()
    {
        // starts each service sequencially
        this.agentHandler.start();
        this.messagingService.start();
        this.executionService.start();
        this.loggingService.start();
    }

    @Override
    public void shutdown()
    {
        // shutdown each service sequencially
        this.agentHandler.shutdown();
        this.messagingService.shutdown();
        this.executionService.shutdown();
        this.loggingService.shutdown();
    }

    @Override
    protected void initParameters(Map<String, Object> parameters)
    {
        this.messagingService = this.readParameter(MESSAGING_SERVICE_CONFIG_KEY, parameters);
        this.executionService = this.readParameter(EXECUTION_SERVICE_CONFIG_KEY, parameters);
        this.agentHandler = this.readParameter(AGENT_HANDLER_SERVICE_CONFIG_KEY, parameters);
        this.loggingService = this.readParameter(LOGGING_SERVICE_CONFIG_KEY, parameters);
    }

    @Override
    public ILoggingService<M> getLoggingService() {
        return this.loggingService;
    }

}
