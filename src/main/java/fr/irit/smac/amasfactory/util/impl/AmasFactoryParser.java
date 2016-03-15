package fr.irit.smac.amasfactory.util.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;
import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.util.IAmasFactoryParser;

public class AmasFactoryParser implements IAmasFactoryParser {

	public static final String INFRA_CONF_KEY = "infrastructure";
	public static final String AGENTS_CONF_KEY = "agents";
	public static final String CLASS_NAME_CONF_KEY = "className";
	public static final String CONF_KEY = "configuration";
	public static final String SERVICES_CONF_KEY = "services";
	public static final String MSG_CLASS_CONFIG_KEY = "messageClassName";
	public final static String NB_THREAD_CONFIG_KEY = "nbThreads";
	public final static String AGENT_MESSAGE_CONTENT = "messageContent";

	private JsonElement configuration;
	private JsonObject agents;
	private JsonElement messagingServiceJson;
	private JsonElement executionServiceJson;
	private JsonElement handlerServiceJson;
	private JsonElement loggingServiceJson;
	private JsonElement infrastructureJson;
	private HashMap<String, JsonElement> agentsJson;

	private AmasFactoryParser() {

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
		this.messagingServiceJson = services.getAsJsonObject(IInfrastructure.MESSAGING_SERVICE_CONFIG_KEY);
		this.handlerServiceJson = services.getAsJsonObject(IInfrastructure.AGENT_HANDLER_SERVICE_CONFIG_KEY);
		this.loggingServiceJson = services.getAsJsonObject(IInfrastructure.LOGGING_SERVICE_CONFIG_KEY);
		this.executionServiceJson = services.getAsJsonObject(IInfrastructure.EXECUTION_SERVICE_CONFIG_KEY);
	}

	private void setJsonAgents() {

		this.agents = this.configuration.getAsJsonObject().getAsJsonObject(AGENTS_CONF_KEY);
	}

	public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M> instantiateAndInitInfrastructure() {

		IInfrastructure<A, M> infrastructure = this.createInstanceFromClassNameField(this.infrastructureJson);
		infrastructure.init(null);
		infrastructure.start();

		return infrastructure;
	}

	@SuppressWarnings("unchecked")
	public <A extends IInfrastructureAgent<M>, M> IInfraService<A, M> instantiateAndInitService(
			IAgentSideInfrastructure<M> infrastructure, JsonElement jsonService) {

		IInfraService<A, M> service = this.createInstanceFromClassNameField(jsonService);
		service.init((IInfrastructure<A, M>) infrastructure);

		return service;
	}

	public <A extends IInfrastructureAgent<M>, M> void instantiateAndInitAgents(IInfrastructure<A, M> infrastructure) {

		agentsJson = new HashMap<String, JsonElement>();
		for (Entry<String, JsonElement> entry : agents.entrySet()) {
			String agentId = entry.getKey();
			JsonObject agentConf = entry.getValue().getAsJsonObject();

			agentsJson.put(agentId, agentConf);

			@SuppressWarnings("unchecked")
			A agent = (A) this.createInstanceFromClassNameField(agentConf);
			agent.init((IInfrastructure<A, M>) infrastructure, agentId);
			infrastructure.getAgentHandler().addAgent(agent);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T createInstanceFromClassNameField(JsonElement configuration) {

		String className = null;
		try {
			className = configuration.getAsJsonObject().get(CLASS_NAME_CONF_KEY).getAsString();

			return (T) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new IllegalArgumentException("Bad class name : " + className, e);
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

	public int getExecutionServiceNbThreads() {

		return executionServiceJson.getAsJsonObject().get(CONF_KEY).getAsJsonObject().get(NB_THREAD_CONFIG_KEY)
				.getAsInt();
	}

	public String getMessagingServiceMessageClassName() {

		return messagingServiceJson.getAsJsonObject().get(CONF_KEY).getAsJsonObject().get(MSG_CLASS_CONFIG_KEY)
				.getAsString();

	}

	public String getAgentMessageContent(String id) {

		return agentsJson.get(id).getAsJsonObject().get(CONF_KEY).getAsJsonObject().get(AGENT_MESSAGE_CONTENT)
				.getAsString();
	}
}
