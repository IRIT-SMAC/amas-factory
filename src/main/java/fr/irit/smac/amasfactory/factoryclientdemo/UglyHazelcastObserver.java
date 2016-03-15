package fr.irit.smac.amasfactory.factoryclientdemo;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import fr.irit.smac.amasfactory.agent.IKnowledge;

public class UglyHazelcastObserver {
	public static final void main(String[] args) {
		Config cfg = new Config();
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
		IMap<String, IKnowledge> mapKnowledge = instance.getMap("knowledge");
		
		for (String id : mapKnowledge.keySet()){
			System.out.println("id=" +id +"\tknowledge=" + mapKnowledge.get(id) +"\tknoID=" +mapKnowledge.get(id).getId());
		}
	}
}
