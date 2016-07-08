package fr.irit.smac.amasfactory.service.datasharing.impl;

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
public class EmptySharingService<A>
    implements IDataSharingService<A> {

    @Override
    public void agentAdded(A agent) {
        // empty
    }

    @Override
    public void agentRemoved(A agent) {
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
    public void setAgentHandlerService(IAgentHandlerService<A> agentHandlerService) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setExecutionService(IExecutionService<A> executionService) {
        // TODO Auto-generated method stub

    }

}
