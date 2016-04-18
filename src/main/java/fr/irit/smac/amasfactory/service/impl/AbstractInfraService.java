package fr.irit.smac.amasfactory.service.impl;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.impl.BasicInfrastructure;
import fr.irit.smac.amasfactory.service.IInfraService;

public abstract class AbstractInfraService<A extends IInfrastructureAgent<M>, M> implements IInfraService<A, M> {

    protected IInfrastructure<A, M> infrastructure;

    protected IInfrastructure<A, M> getInfrastructure() {
        return this.infrastructure;
    }

    @Override
    public void setInfrastructure(BasicInfrastructure<A, M> basicInfrastructure) {

        this.infrastructure = basicInfrastructure;
    }

}
