package fr.irit.smac.amasfactory;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;
import fr.irit.smac.amasfactory.util.parser.impl.AmasFactoryParser;

public interface IInfrastructure<A extends IInfrastructureAgent<M>,M> extends IAgentSideInfrastructure<M>, IInfraService<A, M>
{   
    
    public IAgentHandlerService<A,M> getAgentHandler();
    
    public IExecutionService<A,M> getExecutionService();

    
//    public <S extends IInfraService<A, M>> S getService(String serviceId);
}
