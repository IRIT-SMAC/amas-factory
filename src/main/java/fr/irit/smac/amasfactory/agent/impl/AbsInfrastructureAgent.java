package fr.irit.smac.amasfactory.agent.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;


public abstract class AbsInfrastructureAgent<M> implements IInfrastructureAgent<M>
{
	@JsonProperty
    private String id;
    private IAgentSideInfrastructure<M> infrastructure;

    protected IAgentSideInfrastructure<M> getInfra()
    {
        return this.infrastructure;
    }

    @Override
    public String getId()
    {
        return this.id;
    }
    
    protected abstract void initParameters();
    
    @Override
    public void init(IAgentSideInfrastructure<M> infrastructure, String id)
    {
        this.infrastructure = infrastructure;
        this.id = id;
        
        this.initParameters();
    }

}
