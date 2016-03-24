package fr.irit.smac.amasfactory;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;


public interface IInfrastructure<A extends IInfrastructureAgent<M>,M> extends IAgentSideInfrastructure<M>, IInfraService<A, M>
{   
    
    public IAgentHandlerService<A,M> getAgentHandler();
    
    public IExecutionService<A,M> getExecutionService();

    
//    public <S extends IInfraService<A, M>> S getService(String serviceId);
}
