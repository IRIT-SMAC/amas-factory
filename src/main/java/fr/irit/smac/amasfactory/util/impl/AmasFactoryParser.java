package fr.irit.smac.amasfactory.util.impl;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.util.IAmasFactoryParser;

public class AmasFactoryParser implements IAmasFactoryParser{

    public static final String INFRA_CONF_KEY    = "infrastructure";
    public static final String AGENTS_CONF_KEY   = "agents";
    public static final String CLASS_NAME_CONF_KEY = "className";
    public static final String CONF_KEY = "configuration";
    public static final String SERVICES_CONF_KEY = "services";

    private JsonElement configuration;
    private JsonObject infrastructure;
    private JsonObject agents;
    private JsonObject services;
	private JsonObject messagingService;
	private JsonObject executionService;
	private JsonObject handlerService;
	private JsonObject loggingService;
    
    private AmasFactoryParser() {
    	
    }
    
    private static AmasFactoryParser INSTANCE = new AmasFactoryParser();
    
    public static AmasFactoryParser getInstance() {
    	return INSTANCE;
    }
    
    public void init(InputStream configuration) {
    	
        this.configuration = new JsonParser().parse(new InputStreamReader(configuration));
        this.infrastructure = this.configuration.getAsJsonObject().getAsJsonObject(INFRA_CONF_KEY);
        this.agents = this.configuration.getAsJsonObject().getAsJsonObject(AGENTS_CONF_KEY);
        this.services = this.configuration.getAsJsonObject().getAsJsonObject(SERVICES_CONF_KEY);
        this.messagingService = this.services.getAsJsonObject(IInfrastructure.MESSAGING_SERVICE_CONFIG_KEY);
        this.executionService = this.services.getAsJsonObject(IInfrastructure.EXECUTION_SERVICE_CONFIG_KEY);
        this.handlerService = this.services.getAsJsonObject(IInfrastructure.AGENT_HANDLER_SERVICE_CONFIG_KEY);
        this.loggingService = this.services.getAsJsonObject(IInfrastructure.LOGGING_SERVICE_CONFIG_KEY);
        
        System.out.println(this.executionService);
    }
    
    public JsonElement getConfiguration() {
    	return configuration;
    }
    
    public JsonObject getInfrastructure() {
    	return infrastructure;
    }
    
    public JsonObject getAgents() {
    	return agents;
    }
    
    public JsonObject getServices() {
    	return services;
    }

	public JsonObject getMessagingService() {
		return messagingService;
	}

	public JsonObject getExecutionService() {
		return executionService;
	}

	public JsonObject getHandlerService() {
		return handlerService;
	}

	public JsonObject getLoggingService() {
		return loggingService;
	}
}
