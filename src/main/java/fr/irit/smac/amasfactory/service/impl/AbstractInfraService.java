package fr.irit.smac.amasfactory.service.impl;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.impl.BasicInfrastructure;
import fr.irit.smac.amasfactory.service.IInfraService;

/**
 * AbstractInfraService is an abstract class used by subclasses implementing the
 * infrastructure or a service.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
public abstract class AbstractInfraService<A extends IAgent<M>, M> implements IInfraService<A, M> {

    protected IInfrastructure<A, M> infrastructure;

    /**
     * Gets the infrastructure.
     *
     * @return the infrastructure
     */
    protected IInfrastructure<A, M> getInfrastructure() {
        return this.infrastructure;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.amasfactory.service.IInfraService#setInfrastructure(fr.irit.
     * smac.amasfactory.impl.BasicInfrastructure)
     */
    @Override
    public void setInfrastructure(BasicInfrastructure<A, M> basicInfrastructure) {

        this.infrastructure = basicInfrastructure;
    }

}
