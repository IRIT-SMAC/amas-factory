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
    public static final String SERVICES_CONF_KEY = "services";
    public static final String AGENTS_CONF_KEY   = "agents";
    public static final String CLASS_NAME_CONF_KEY = "className";
    public static final String CONF_KEY = "configuration";

    private AmasFactoryInstantiator amasFactoryInstantiator;
    
    @SuppressWarnings("unchecked")
    @Override
    public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M> createInfrastructure(
        InputStream configuration) {

        JsonElement confObj = new JsonParser().parse(new InputStreamReader(configuration));

        // 1
        JsonObject infraConf = confObj.getAsJsonObject().getAsJsonObject(INFRA_CONF_KEY);
        JsonObject servicesConf = confObj.getAsJsonObject().getAsJsonObject(SERVICES_CONF_KEY);
        JsonObject agentsConf = confObj.getAsJsonObject().getAsJsonObject(AGENTS_CONF_KEY);

        this.amasFactoryInstantiator = AmasFactoryInstantiator.getInstance();

        IInfrastructure<A, M> infrastructure = amasFactoryInstantiator.createInstanceFromClassNameField(infraConf);
  
        IMessagingService<M> msgService = (IMessagingService<M>) amasFactoryInstantiator.instantiateAndInitService(
            infrastructure, servicesConf.getAsJsonObject(IInfrastructure.MESSAGING_SERVICE_CONFIG_KEY));

        IExecutionService<A, M> execService = (IExecutionService<A, M>) amasFactoryInstantiator
            .instantiateAndInitService(
                infrastructure, servicesConf.getAsJsonObject(IInfrastructure.EXECUTION_SERVICE_CONFIG_KEY));

        IAgentHandlerService<A, M> agentHandlerService = (IAgentHandlerService<A, M>) amasFactoryInstantiator
            .instantiateAndInitService(
                infrastructure, servicesConf.getAsJsonObject(IInfrastructure.AGENT_HANDLER_SERVICE_CONFIG_KEY));

        ILoggingService<M> loggingService = (ILoggingService<M>) amasFactoryInstantiator.instantiateAndInitService(
            infrastructure, servicesConf.getAsJsonObject(IInfrastructure.LOGGING_SERVICE_CONFIG_KEY));

        infrastructure.init(
            null,
            ImmutableMap.<String, Object> builder()
                .put(IInfrastructure.AGENT_HANDLER_SERVICE_CONFIG_KEY, agentHandlerService)
                .put(IInfrastructure.EXECUTION_SERVICE_CONFIG_KEY, execService)
                .put(IInfrastructure.MESSAGING_SERVICE_CONFIG_KEY, msgService)
                .put(IInfrastructure.LOGGING_SERVICE_CONFIG_KEY, loggingService)
                .put(BasicAmasFactory.CONF_KEY, infraConf.getAsJsonObject(BasicAmasFactory.CONF_KEY))
                .build());

        infrastructure.start();

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
