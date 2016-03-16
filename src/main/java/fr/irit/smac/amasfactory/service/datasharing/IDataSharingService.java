package fr.irit.smac.amasfactory.service.datasharing;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener;

public interface IDataSharingService <A extends IInfrastructureAgent<M>,M> extends IAgentEventListener<A> , IInfraService<A, M>{

}

