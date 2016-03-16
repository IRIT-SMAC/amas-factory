package fr.irit.smac.amasfactory.service.datasharing.impl;

import com.google.gson.JsonElement;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.util.impl.HazelcastKnowledgeAccessor;

/**
 * Create a Map with Hazelcast and fill it with the data from the agents knowledge.
 * Update the shared data at the end of each step.
 * Automatically add to the map the knowledge of newly created agent.
 * Automatically remove from the map the knowledge of deleted agent.
 * 
 * @author SVI
 *
 * @param <A>
 * @param <M>
 */
public class HazelcastSharingService<A extends IInfrastructureAgent<M>, M> extends AbstractInfraService<A, M>
		implements IDataSharingService<A, M> {

	HazelcastKnowledgeAccessor hazelcastKnowledgeAccessor;

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
		
		// Register to share the agent knowledge at the end of each step.
		this.getInfrastructure().getExecutionService().addPostStepHook(generatePostStepTask(this.getInfrastructure()));
	}

	private Runnable generatePostStepTask(IInfrastructure<A, M> infrastructure) {
		return new Runnable() {
            @Override
            public void run() {
        		for (A agent : infrastructure.getAgentHandler().getAgents()){
        			hazelcastKnowledgeAccessor.registerKnowledge(agent.getInnerKnowledge());
        			//System.out.println("registering " + agent.getId() );
        		};
            }
        };
	}

	@Override
	public void shutdown() {
		// TODO clean the map?

	}

	@Override
	protected void initParameters(JsonElement parameters) {
		// TODO Auto-generated method stub

	}

}
