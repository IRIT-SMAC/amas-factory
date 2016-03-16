package fr.irit.smac.amasfactory.service.datasharing.impl;

import java.util.Set;

import com.google.gson.JsonElement;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.util.impl.HazelcastKnowledgeAccessor;

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

		this.getInfrastructure().getAgentHandler().addAgentEventListener(this);
		this.getInfrastructure().getExecutionService().addPostStepHook(generatePostStepTask(this.getInfrastructure()));
	}

	private Runnable generatePostStepTask(IInfrastructure<A, M> infrastructure) {
		return new Runnable() {
            @Override
            public void run() {
        		for (A agent : infrastructure.getAgentHandler().getAgents()){
        			hazelcastKnowledgeAccessor.registerKnowledge(agent.getInnerKnowledge());
        			System.out.println("registering " + agent.getId() );
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
