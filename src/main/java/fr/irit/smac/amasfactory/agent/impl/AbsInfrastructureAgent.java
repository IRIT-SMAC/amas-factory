package fr.irit.smac.amasfactory.agent.impl;

import com.google.gson.JsonObject;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.agent.IKnowledge;

public abstract class AbsInfrastructureAgent<M> implements IInfrastructureAgent<M>
{
    private String id;
    private IAgentSideInfrastructure<M> infrastructure;
    private IKnowledge knowledge;
    
    
    protected IAgentSideInfrastructure<M> getInfra()
    {
        return this.infrastructure;
    }

    @Override
    public String getId()
    {
        return this.id;
    }
    
    protected abstract void initParameters(JsonObject configuration);
    
    @Override
    public void init(IAgentSideInfrastructure<M> infrastructure, String id, IKnowledge knowledge, JsonObject configuration)
    {
        this.infrastructure = infrastructure;
        this.id = id;
        this.knowledge = knowledge;
        
        this.initParameters(configuration);
    }

    @Override
    public IKnowledge getInnerKnowledge(){
    	return this.knowledge;
    }
    
}
