package fr.irit.smac.amasfactory.service.datasharing.impl;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;

/**
 * An empty implementation, for sharingless projects.
 * 
 * @author SVI
 *
 * @param <A>
 * @param <M>
 */
public class EmptySharingService<A extends IAgent<M>, M> extends AbstractInfraService<A, M>
    implements IDataSharingService<A, M> {

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

}
