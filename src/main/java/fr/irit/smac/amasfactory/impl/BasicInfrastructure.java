package fr.irit.smac.amasfactory.impl;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;

public class BasicInfrastructure<A extends IInfrastructureAgent<M>, M> extends AbstractInfraService<A, M>
		implements IInfrastructure<A, M> {
	
	private IMessagingService<M> messagingService;
	private IAgentHandlerService<A, M> agentHandlerService;
	private IExecutionService<A, M> executionService;
	private ILoggingService<M> loggingService;
	private IDataSharingService<A, M> hazelcastService;

	public BasicInfrastructure() {
		super();
	}

	@Override
	public IMessagingService<M> getMessagingService() {
		return this.messagingService;
	}

	@Override
	public IAgentHandlerService<A, M> getAgentHandler() {
		return this.agentHandlerService;
	}

	@Override
	public IExecutionService<A, M> getExecutionService() {
		return this.executionService;
	}

	public void setInfrastructure(BasicInfrastructure<A, M> basicInfrastructure) {
		
	}


	public IDataSharingService<A, M> getDataSharingService() {
		return this.hazelcastService;
	}
	
	@Override
	public void init(IInfrastructure<A, M> infrastructure) {
		if (infrastructure != null) {
			throw new IllegalArgumentException("An instance of infrastructure already exists");
		}
		super.init(this);
	}

	public void setAgentHandlerService(IAgentHandlerService<A, M> agentHandlerService) {
		
		agentHandlerService.setInfrastructure(this);
		this.agentHandlerService = agentHandlerService;
	}
	
	public void setExecutionService(IExecutionService<A, M>  executionService) {
		
		executionService.setInfrastructure(this);
		this.executionService = executionService;
	}
	
	@SuppressWarnings("unchecked")
	public void setMessagingService(IMessagingService<M> messagingService) {
		
		messagingService.setInfrastructure((BasicInfrastructure<IInfrastructureAgent<M>, M>) this);
		this.messagingService = messagingService;
	}
	
	@SuppressWarnings("unchecked")
	public void setLoggingService(ILoggingService<M> loggingService) {
		
		loggingService.setInfrastructure((BasicInfrastructure<IInfrastructureAgent<M>, M>) this);
		this.loggingService = loggingService;
	}
	
	public void setHazelcastService(IDataSharingService<A, M> hazelcastService) {
		
		hazelcastService.setInfrastructure(this);
		this.hazelcastService = hazelcastService;
	}
	
	@Override
	public void start() {
		// starts each service sequencially
		this.agentHandlerService.start();
		this.executionService.start();
		this.messagingService.start();
		this.loggingService.start();
		this.agentHandlerService.setInfrastructureAgent(this);
		this.hazelcastService.start();
	}

	@Override
	public void shutdown() {
		// shutdown each service sequencially
		this.agentHandlerService.shutdown();
		this.messagingService.shutdown();
		this.executionService.shutdown();
		this.loggingService.shutdown();
		this.hazelcastService.shutdown();
	}

	@Override
	protected void initParameters() {
		infrastructure.start();
	}

	@Override
	public ILoggingService<M> getLoggingService() {
		return this.loggingService;
	}

}
