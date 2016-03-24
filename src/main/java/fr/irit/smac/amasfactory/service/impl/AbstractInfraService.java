package fr.irit.smac.amasfactory.service.impl;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.impl.BasicInfrastructure;
import fr.irit.smac.amasfactory.service.IInfraService;

public abstract class AbstractInfraService<A extends IInfrastructureAgent<M>,M> implements IInfraService<A,M>
{

    public IInfrastructure<A,M> infrastructure;

    @Override
    public void init(IInfrastructure<A,M> infrastructure)
    {
        this.infrastructure = infrastructure;
        
        this.initParameters();
        
    }

    protected abstract void initParameters();
    
    protected IInfrastructure<A,M> getInfrastructure()
    {
        return this.infrastructure;
    }
    
	public void setInfrastructure(BasicInfrastructure<A, M> basicInfrastructure) {
		
		this.infrastructure = basicInfrastructure;
	}

}
