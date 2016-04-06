package fr.irit.smac.amasfactory.util.hazelcast.impl;

import java.util.Map;
import java.util.Set;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.util.IHazelcastKnowledgeAccessor;

/**
 * Class allowing to connect to the shared knowledge map and to to basic
 * operation on it.
 * 
 * @author SVI
 *
 */
public class HazelcastKnowledgeAccessor implements IHazelcastKnowledgeAccessor {

	Map<String, IKnowledge> mapKnowledge;

	HazelcastInstance instance;

	public HazelcastKnowledgeAccessor() {
		Config cfg = new Config();
		instance = Hazelcast.newHazelcastInstance(cfg);
		mapKnowledge = instance.getMap("knowledge");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.irit.smac.amasfactory.util.impl.IHazelcastKnowledgeAccessor#
	 * registerKnowledge(fr.irit.smac.amasfactory.agent.IKnowledge)
	 */
	@Override
	public void registerKnowledge(IKnowledge knowledge) {
		this.mapKnowledge.put(knowledge.getId(), knowledge);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.irit.smac.amasfactory.util.impl.IHazelcastKnowledgeAccessor#
	 * removeKnowledge(java.lang.String)
	 */
	@Override
	public void removeKnowledge(String knowledgeId) {
		this.mapKnowledge.remove(knowledgeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.irit.smac.amasfactory.util.impl.IHazelcastKnowledgeAccessor#
	 * getKnowledge(java.lang.String)
	 */
	@Override
	public IKnowledge getKnowledge(String knowledgeId) {
		return mapKnowledge.get(knowledgeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.irit.smac.amasfactory.util.impl.IHazelcastKnowledgeAccessor#
	 * getKnowledgeIdSet()
	 */
	@Override
	public Set<String> getKnowledgeIdSet() {
		return mapKnowledge.keySet();
	}

	@Override
	public Map<String, IKnowledge> getKnowledgeMap() {
		return mapKnowledge;
	}

	@Override
	public void shutdownInstance() {
		this.instance.shutdown();
	}
}
