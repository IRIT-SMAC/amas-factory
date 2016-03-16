package fr.irit.smac.amasfactory.util.impl;

import java.util.Map;
import java.util.Set;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import fr.irit.smac.amasfactory.agent.IKnowledge;

/**
 * Class allowing to connect to the shared knowledge map and to to basic operation on it.
 * @author SVI
 *
 */
public class HazelcastKnowledgeAccessor {
	Map<String, IKnowledge> mapKnowledge;

	public HazelcastKnowledgeAccessor() {
		Config cfg = new Config();
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
		mapKnowledge = instance.getMap("knowledge");
	}
	
	public void registerKnowledge(IKnowledge knowledge) {
		this.mapKnowledge.put(knowledge.getId(), knowledge);
	}

	public void removeKnowledge(String knowledgeId) {
		this.mapKnowledge.remove(knowledgeId);
	}
	
	public IKnowledge getKnowledge(String knowledgeId){
		return mapKnowledge.get(knowledgeId);
	}
	
	public Set<String> getKnowledgeIdSet(){
		return mapKnowledge.keySet();
	}
}
