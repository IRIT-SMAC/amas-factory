package fr.irit.smac.amasfactory.service.impl;

import java.util.Map;

import com.google.gson.JsonElement;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;

public abstract class AbstractInfraService<A extends IInfrastructureAgent<M>,M> implements IInfraService<A,M>
{

    public IInfrastructure<A,M> infrastructure;

    @Override
    public void init(IInfrastructure<A,M> infrastructure, JsonElement parameters)
    {
        this.infrastructure = infrastructure;
        
        this.initParameters(parameters);
        
    }

    protected abstract void initParameters(JsonElement parameters);

    protected IInfrastructure<A,M> getInfrastructure()
    {
        return this.infrastructure;
    }
    
    @SuppressWarnings("unchecked")
    protected <T> T readParameter(String parameterName, Map<String, Object> parameters)  {
        if(parameters != null && parameters.containsKey(parameterName)) {
           return (T)parameters.get(parameterName);
        } else {
            throw new IllegalArgumentException(
                "Cannot found configuration value for " + parameterName
            );
        }
    }

}
