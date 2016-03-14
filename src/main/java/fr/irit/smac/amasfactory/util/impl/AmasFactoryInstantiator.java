package fr.irit.smac.amasfactory.util.impl;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;
import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.IJSonInitialisable;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.util.IAmasFactoryInstantiator;

public class AmasFactoryInstantiator implements IAmasFactoryInstantiator {

    public static final String CONF_KEY = "configuration";
    public static final String CLASS_NAME_CONF_KEY = "className";

    private AmasFactoryInstantiator() {

    }
    
    private static AmasFactoryInstantiator INSTANCE = new AmasFactoryInstantiator();
   
    public static AmasFactoryInstantiator getInstance() {
        return INSTANCE;
    }
    
    @Override
    public <T> T instantiateAndInitObjectFromConfig(JsonObject configuration) {
        
        T object = this.createInstanceFromClassNameField(configuration);
        
        JsonElement config = configuration.get(CONF_KEY);
        
        if (object instanceof IJSonInitialisable && config != null) {
            ((IJSonInitialisable) object).init(config.getAsJsonObject());
        }
        
        return object;
    }

    @Override
    public <M> IInfrastructureAgent<M> instantiateAndInitAgentFromConfig(IAgentSideInfrastructure<M> infrastructure,
        String agentId, JsonObject configuration) {
        
        IInfrastructureAgent<M> agent = this.createInstanceFromClassNameField(configuration);
        agent.init(infrastructure, agentId, configuration.get(CONF_KEY).getAsJsonObject());

        return agent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A extends IInfrastructureAgent<M>, M> IInfraService<A, M> instantiateAndInitService(
        IAgentSideInfrastructure<M> infrastructure, JsonObject configuration) {

        IInfraService<A, M> service = this.createInstanceFromClassNameField(configuration);
        service.init(
            (IInfrastructure<A, M>) infrastructure,
            this.convertToMap(configuration.get(CONF_KEY).getAsJsonObject()
        ));
        
        return service;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T createInstanceFromClassNameField(JsonObject conf) {
        
        String className = null;
        try
        {
            className = conf.get(CLASS_NAME_CONF_KEY).getAsString();
            return (T) Class.forName(className).newInstance();
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
        {
            throw new IllegalArgumentException("Bad class name : "+className,e);
        }
    }

    private Map<String,Object> convertToMap(JsonObject conf) {
        
        Builder<String, Object> builder = ImmutableMap.<String,Object>builder();
        for (Entry<String, JsonElement> entry : conf.entrySet())
        {
            try {
                builder.put(entry.getKey(),entry.getValue().getAsDouble());
            } catch(NumberFormatException e) {
                builder.put(entry.getKey(),entry.getValue().getAsString());
            }
        }
        
        return builder.build();
    }
}
