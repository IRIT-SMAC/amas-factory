package fr.irit.smac.amasfactory.service.datasharing.impl;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;

/**
 * An empty implementation, for sharingless projects.
 * 
 * @author SVI
 *
 * @param <A>
 * @param <M>
 */
public class EmptySharingService
    implements IDataSharingService {

    @Override
    public void agentAdded(IAgent agent) {
        // empty
    }

    @Override
    public void agentRemoved(IAgent agent) {
        // empty
    }

    @Override
    public void start() {
        // empty
    }

    @Override
    public void shutdown() {
        // empty
    }

    @Override
    public void setAgentHandlerService(IAgentHandlerService agentHandlerService) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setExecutionService(IExecutionService executionService) {
        // TODO Auto-generated method stub

    }

}
