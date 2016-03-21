package fr.irit.smac.amasfactory.util.parser.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;
import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.agent.impl.AbsInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.util.parser.IAmasFactoryParser;
import fr.irit.smac.amasfactory.util.parser.deserializer.impl.AmasFactoryDeserializer;

public class AmasFactoryParser implements IAmasFactoryParser {

    /**
     * The config keys that are used to set the different instances of the services
     */
    public final static String MESSAGING_SERVICE_CONFIG_KEY = "messaginService";
    public final static String EXECUTION_SERVICE_CONFIG_KEY = "executionService";
    public final static String AGENT_HANDLER_SERVICE_CONFIG_KEY = "agentHandlerService";
    public final static String LOGGING_SERVICE_CONFIG_KEY = "loggingService";
    
	public static final String INFRA_CONF_KEY = "infrastructure";
	public static final String AGENTS_CONF_KEY = "agents";
	public static final String CLASS_NAME_CONF_KEY = "className";
	public static final String CONF_KEY = "configuration";
	public static final String SERVICES_CONF_KEY = "services";

	protected JsonElement configuration;
	protected JsonObject agents;
	protected JsonElement messagingServiceJson;
	protected JsonElement executionServiceJson;
	protected JsonElement handlerServiceJson;
	protected JsonElement loggingServiceJson;
	protected JsonElement infrastructureJson;
	protected HashMap<String, JsonElement> agentsJson;

	protected AmasFactoryParser() {

	}

	private static AmasFactoryParser INSTANCE = new AmasFactoryParser();

	public static AmasFactoryParser getInstance() {
		return INSTANCE;
	}
	
	public void init(InputStream configuration) {

		this.configuration = new JsonParser().parse(new InputStreamReader(configuration));

		this.setJsonInfra();
		this.setJsonServices();
		this.setJsonAgents();
	}

	private void setJsonInfra() {

		this.infrastructureJson = this.configuration.getAsJsonObject().get(INFRA_CONF_KEY);
	}

	private void setJsonServices() {

		JsonObject services = this.configuration.getAsJsonObject().getAsJsonObject(SERVICES_CONF_KEY);
		this.messagingServiceJson = services.getAsJsonObject(MESSAGING_SERVICE_CONFIG_KEY);
		
		this.handlerServiceJson = services.getAsJsonObject(AGENT_HANDLER_SERVICE_CONFIG_KEY);
		this.loggingServiceJson = services.getAsJsonObject(LOGGING_SERVICE_CONFIG_KEY);
		this.executionServiceJson = services.getAsJsonObject(EXECUTION_SERVICE_CONFIG_KEY);
	}

	private void setJsonAgents() {

		this.agents = this.configuration.getAsJsonObject().getAsJsonObject(AGENTS_CONF_KEY);
	}

	public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M> instantiateAndInitInfrastructure() {
		
		Gson json = new GsonBuilder().registerTypeAdapter(AbstractInfraService.class,new AmasFactoryDeserializer<Object>()).create();
		AbstractInfraService service = json.fromJson(infrastructureJson.getAsJsonObject().toString(), AbstractInfraService.class);
		service.init(null, null);
		
		return (IInfrastructure<A, M>) service;
	}

	public <A extends IInfrastructureAgent<M>, M> IInfraService<A, M> instantiateAndInitService(
			IAgentSideInfrastructure<M> infrastructure, JsonElement jsonService) {


		Gson json = new GsonBuilder().registerTypeAdapter(AbstractInfraService.class,new AmasFactoryDeserializer<Object>()).create();
		IInfraService<A, M> service = json.fromJson(jsonService.getAsJsonObject().toString(), AbstractInfraService.class);
		service.init((IInfrastructure<A, M>)infrastructure, jsonService);
	
		return service;
	}

	public <A extends IInfrastructureAgent<M>, M> void instantiateAndInitAgents(IInfrastructure<A, M> infrastructure) {

		agentsJson = new HashMap<String, JsonElement>();
		for (Entry<String, JsonElement> entry : agents.entrySet()) {
			
			String agentId = entry.getKey();
			JsonObject agentConf = entry.getValue().getAsJsonObject();

			Gson json = new GsonBuilder().registerTypeAdapter(AbsInfrastructureAgent.class,new AmasFactoryDeserializer<A>()).create();
			AbsInfrastructureAgent agent = json.fromJson(agentConf.getAsJsonObject().toString(), AbsInfrastructureAgent.class);
			agent.init((IInfrastructure<A, M>)infrastructure, agentId, agentConf);
			infrastructure.getAgentHandler().addAgent((A) agent);
			
			agentsJson.put(agentId, agentConf);
		}
	}

	public JsonElement getMessagingService() {
		return messagingServiceJson;
	}

	public JsonElement getExecutionService() {
		return executionServiceJson;
	}

	public JsonElement getHandlerService() {
		return handlerServiceJson;
	}

	public JsonElement getLoggingService() {
		return loggingServiceJson;
	}

}
