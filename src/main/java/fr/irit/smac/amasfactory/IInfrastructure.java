package fr.irit.smac.amasfactory;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;

public interface IInfrastructure<A extends IInfrastructureAgent<M>,M> extends IAgentSideInfrastructure<M>, IInfraService<A, M>
{
    /**
     * The config keys that are used to set the different instances of the services
     */
    public final static String MESSAGING_SERVICE_CONFIG_KEY = "messaginService";
    public final static String EXECUTION_SERVICE_CONFIG_KEY = "executionService";
    public final static String AGENT_HANDLER_SERVICE_CONFIG_KEY = "agentHandlerService";
    public final static String LOGGING_SERVICE_CONFIG_KEY = "loggingService";
    
    public final static String DATA_SHARING_SERVICE_CONFIG_KEY = "hazelcastService";
    
    public IAgentHandlerService<A,M> getAgentHandler();
    
    public IExecutionService<A,M> getExecutionService();
    
	public IDataSharingService<A, M> getDataSharingService();
    
//    public <S extends IInfraService<A, M>> S getService(String serviceId);
}
