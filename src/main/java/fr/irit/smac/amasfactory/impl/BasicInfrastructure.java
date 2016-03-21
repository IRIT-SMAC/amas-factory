package fr.irit.smac.amasfactory.impl;

import com.google.gson.JsonElement;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;
import fr.irit.smac.amasfactory.util.parser.impl.AmasFactoryParser;

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
	public void init(IInfrastructure<A, M> infrastructure, JsonElement configuration) {
		if (infrastructure != null) {
			throw new IllegalArgumentException("An instance of infrastructure already exists");
		}
		super.init(this, configuration);
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
	protected void initParameters(JsonElement configuration) {

		IInfrastructure<A, M> infrastructure = this.infrastructure;

		AmasFactoryParser parser = AmasFactoryParser.getInstance();
				
		this.messagingService = (IMessagingService<M>) parser.instantiateAndInitService(infrastructure,
				parser.getMessagingService());

		this.executionService = (IExecutionService<A, M>) parser.instantiateAndInitService(infrastructure,
				parser.getExecutionService());

		this.agentHandler = (IAgentHandlerService<A, M>) parser.instantiateAndInitService(infrastructure,
				parser.getHandlerService());

		this.loggingService = (ILoggingService<M>) parser.instantiateAndInitService(infrastructure,
				parser.getLoggingService());
		
		infrastructure.start();


	}

	@Override
	public ILoggingService<M> getLoggingService() {
		return this.loggingService;
	}

}
