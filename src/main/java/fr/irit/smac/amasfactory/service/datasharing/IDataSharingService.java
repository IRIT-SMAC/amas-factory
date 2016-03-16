package fr.irit.smac.amasfactory.service.datasharing;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener;

/**
 * Interface to share the data of the agents with others. eg.Hazelcast implementation 
 * @author SVI
 *
 * @param <A>
 * @param <M>
 */
public interface IDataSharingService <A extends IInfrastructureAgent<M>,M> extends IAgentEventListener<A> , IInfraService<A, M>{

}

