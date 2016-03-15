package fr.irit.smac.amasfactory.service.datasharing.impl;

import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;

public class HazelcastSharingService<A extends IInfrastructureAgent<M>, M> extends AbstractInfraService<A, M>
		implements IDataSharingService<A, M> {

	Map<String, IKnowledge> mapKnowledge;

	public HazelcastSharingService() {
		Config cfg = new Config();
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
		mapKnowledge = instance.getMap("knowledge");

	}

	@Override
	public void agentAdded(A agent) {
		this.mapKnowledge.put(agent.getId(), agent.getInnerKnowledge());
	}

	@Override
	public void agentRemoved(A agent) {
		this.mapKnowledge.remove(agent.getId());
	}

	@Override
	public void start() {

		this.getInfrastructure().getAgentHandler().addAgentEventListener(this);
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPreStepHook(Runnable task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPostStepHook(Runnable task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePreStepHook(Runnable task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePostStepHook(Runnable task) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Runnable> getPreStepHooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Runnable> getPostStepHooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initParameters(JsonElement parameters) {
		// TODO Auto-generated method stub

	}

}
