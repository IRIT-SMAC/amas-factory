package fr.irit.smac.amasfactory.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.irit.smac.amasfactory.IAmasFactory;
import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.util.impl.AmasFactoryInstantiator;
import fr.irit.smac.amasfactory.util.impl.AmasFactoryParser;

/**
 * 
 * 
 * 
 * @author lemouzy
 *
 */
public class BasicAmasFactory implements IAmasFactory {

    private AmasFactoryInstantiator amasFactoryInstantiator;
    private AmasFactoryParser amasFactoryParser;
    
    @Override
    public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M> createInfrastructure(
        InputStream configuration) {

        this.amasFactoryInstantiator = AmasFactoryInstantiator.getInstance();
        this.amasFactoryParser = AmasFactoryParser.getInstance();
        this.amasFactoryParser.init(configuration);
        
        IInfrastructure<A, M> infrastructure = amasFactoryInstantiator.createInstanceFromClassNameField(this.amasFactoryParser.getInfrastructure());
        infrastructure.init(null, amasFactoryParser.getConfiguration());
        infrastructure.start();
        
        this.createAndInitAgentsCollection(infrastructure, amasFactoryParser.getAgents());

        return infrastructure;
    }

    @SuppressWarnings("unchecked")
    private <A extends IInfrastructureAgent<M>, M> void createAndInitAgentsCollection(
        IInfrastructure<A, M> infrastructure, JsonObject agentsConf) {
        for (Entry<String, JsonElement> entry : agentsConf.entrySet()) {
            String agentId = entry.getKey();
            JsonObject agentConf = entry.getValue().getAsJsonObject();

            A agent = (A) this.amasFactoryInstantiator.instantiateAndInitAgentFromConfig(infrastructure, agentId, agentConf);
            infrastructure.getAgentHandler().addAgent(agent);
        }
    }
}
