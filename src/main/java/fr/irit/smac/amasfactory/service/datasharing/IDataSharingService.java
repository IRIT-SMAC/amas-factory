package fr.irit.smac.amasfactory.service.datasharing;

import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;

/**
 * Interface to share the data of the agents with others. eg.Hazelcast
 * implementation
 * 
 * @author SVI
 */
public interface IDataSharingService
    extends IAgentEventListener, IInfraService {

    void setAgentHandlerService(IAgentHandlerService agentHandlerService);

    void setExecutionService(IExecutionService executionService);

}
