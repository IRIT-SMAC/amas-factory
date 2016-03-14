package fr.irit.smac.amasfactory.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.irit.smac.amasfactory.IAmasFactory;
import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;
import fr.irit.smac.amasfactory.util.impl.AmasFactoryInstantiator;

/**
 * 
 * 
 * 
 * @author lemouzy
 *
 */
public class BasicAmasFactory implements IAmasFactory {

    public static final String INFRA_CONF_KEY    = "infrastructure";
    public static final String AGENTS_CONF_KEY   = "agents";
    public static final String CLASS_NAME_CONF_KEY = "className";
    public static final String CONF_KEY = "configuration";

    private AmasFactoryInstantiator amasFactoryInstantiator;
    
    @Override
    public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M> createInfrastructure(
        InputStream configuration) {

        JsonElement confObj = new JsonParser().parse(new InputStreamReader(configuration));

        this.amasFactoryInstantiator = AmasFactoryInstantiator.getInstance();
        
        JsonObject infraConf = confObj.getAsJsonObject().getAsJsonObject(INFRA_CONF_KEY);
        IInfrastructure<A, M> infrastructure = amasFactoryInstantiator.createInstanceFromClassNameField(infraConf);
        infrastructure.init(null,confObj);
        infrastructure.start();
        
        JsonObject agentsConf = confObj.getAsJsonObject().getAsJsonObject(AGENTS_CONF_KEY);
        this.createAndInitAgentsCollection(infrastructure, agentsConf);

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
