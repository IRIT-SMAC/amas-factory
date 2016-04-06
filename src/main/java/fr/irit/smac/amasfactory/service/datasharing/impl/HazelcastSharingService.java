package fr.irit.smac.amasfactory.service.datasharing.impl;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.util.IHazelcastKnowledgeAccessor;
import fr.irit.smac.amasfactory.util.hazelcast.impl.HazelcastKnowledgeAccessor;

/**
 * Create a Map with Hazelcast and fill it with the data from the agents
 * knowledge. Update the shared data at the end of each step. Automatically add
 * to the map the knowledge of newly created agent. Automatically remove from
 * the map the knowledge of deleted agent.
 * 
 * @author SVI
 *
 * @param <A>
 * @param <M>
 */
public class HazelcastSharingService<A extends IInfrastructureAgent<M>, M> extends AbstractInfraService<A, M>
		implements IDataSharingService<A, M> {

	IHazelcastKnowledgeAccessor hazelcastKnowledgeAccessor;

	// BEFORE_ITERATION AFTER_ITERATION
	public static final String PERSISTENCE_UPDATE_POLICY = "AFTER_ITERATION";

	private Runnable persistencePolicy;
	
	public HazelcastSharingService() {
		this.hazelcastKnowledgeAccessor = new HazelcastKnowledgeAccessor();
	}

	@Override
	public void agentAdded(A agent) {
		this.hazelcastKnowledgeAccessor.registerKnowledge(agent.getInnerKnowledge());
	}

	@Override
	public void agentRemoved(A agent) {
		this.hazelcastKnowledgeAccessor.removeKnowledge(agent.getId());
	}

	@Override
	public void start() {

		// Register to react to agents creation, deletion.
		this.getInfrastructure().getAgentHandler().addAgentEventListener(this);

		this.persistencePolicy =  generatePersistenceUpdatePolicy(this.getInfrastructure());
		
		// Register to share the agent knowledge at the end of each step.
		switch (PERSISTENCE_UPDATE_POLICY) {
		case "AFTER_ITERATION":
			this.getInfrastructure().getExecutionService()
					.addPostStepHook(this.persistencePolicy );
			break;
		case "BEFORE_ITERATION":
			this.getInfrastructure().getExecutionService()
					.addPreStepHook(this.persistencePolicy );
			break;
		default:
			break;
		}

	}

	/**
	 * Persist the data in the map at the end of each iteration.
	 * 
	 * @param infrastructure
	 * @return
	 */
	private Runnable generatePersistenceUpdatePolicy(IInfrastructure<A, M> infrastructure) {
		return new Runnable() {
			@Override
			public void run() {
				for (A agent : infrastructure.getAgentHandler().getAgents()) {
					hazelcastKnowledgeAccessor.registerKnowledge(agent.getInnerKnowledge());
					// System.out.println("registering " + agent.getId() );
				}
				;
			}
		};
	}

	@Override
	public void shutdown() {
		
		this.getInfrastructure().getAgentHandler().removeAgentEventListener(this);
		
		switch (PERSISTENCE_UPDATE_POLICY) {
		case "AFTER_ITERATION":
			this.getInfrastructure().getExecutionService()
					.removePostStepHook(this.persistencePolicy );
			break;
		case "BEFORE_ITERATION":
			this.getInfrastructure().getExecutionService()
					.removePreStepHook(this.persistencePolicy );
			break;
		default:
			break;
		}

	}

	@Override
	protected void initParameters() {
	}

}
