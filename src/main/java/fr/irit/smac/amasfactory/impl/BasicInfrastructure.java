package fr.irit.smac.amasfactory.impl;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;
import fr.irit.smac.amasfactory.util.impl.AmasFactoryInstantiator;

public class BasicInfrastructure<A extends IInfrastructureAgent<M>, M> extends AbstractInfraService<A, M>
		implements IInfrastructure<A, M> {
	private IMessagingService<M> messagingService;
	private IAgentHandlerService<A, M> agentHandler;
	private IExecutionService<A, M> executionService;
	private ILoggingService<M> loggingService;

	public BasicInfrastructure() {
		super();
		this.messagingService = null;
		this.agentHandler = null;
		this.executionService = null;
	}

	@Override
	public IMessagingService<M> getMessagingService() {
		return this.messagingService;
	}

	@Override
	public IAgentHandlerService<A, M> getAgentHandler() {
		return this.agentHandler;
	}

	@Override
	public IExecutionService<A, M> getExecutionService() {
		return this.executionService;
	}

	@Override
	public void init(IInfrastructure<A, M> infrastructure, JsonElement parameters) {
		if (infrastructure != null) {
			throw new IllegalArgumentException("An instance of infrastructure already exists");
		}
		super.init(this, parameters);
	}

	@Override
	public void start() {
		// starts each service sequencially
		this.agentHandler.start();
		this.messagingService.start();
		this.executionService.start();
		this.loggingService.start();
	}

	@Override
	public void shutdown() {
		// shutdown each service sequencially
		this.agentHandler.shutdown();
		this.messagingService.shutdown();
		this.executionService.shutdown();
		this.loggingService.shutdown();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void initParameters(JsonElement parameters) {

        AmasFactoryInstantiator amasFactoryInstantiator = AmasFactoryInstantiator.getInstance();

        JsonObject servicesConf = parameters.getAsJsonObject().getAsJsonObject(SERVICES_CONF_KEY);

        IInfrastructure<A,M> infrastructure = this.infrastructure;

		this.messagingService = (IMessagingService<M>) amasFactoryInstantiator.instantiateAndInitService(
				infrastructure, servicesConf.getAsJsonObject(IInfrastructure.MESSAGING_SERVICE_CONFIG_KEY));
		
		this.executionService = (IExecutionService<A, M>) amasFactoryInstantiator
				.instantiateAndInitService(infrastructure,
						servicesConf.getAsJsonObject(IInfrastructure.EXECUTION_SERVICE_CONFIG_KEY));
		
		this.agentHandler = (IAgentHandlerService<A, M>) amasFactoryInstantiator
				.instantiateAndInitService(infrastructure,
						servicesConf.getAsJsonObject(IInfrastructure.AGENT_HANDLER_SERVICE_CONFIG_KEY));
		
		this.loggingService = (ILoggingService<M>) amasFactoryInstantiator.instantiateAndInitService(
				infrastructure, servicesConf.getAsJsonObject(IInfrastructure.LOGGING_SERVICE_CONFIG_KEY));
	}

	@Override
	public ILoggingService<M> getLoggingService() {
		return this.loggingService;
	}

}
