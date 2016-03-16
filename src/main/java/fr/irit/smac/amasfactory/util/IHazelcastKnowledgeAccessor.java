package fr.irit.smac.amasfactory.util;

import java.util.Set;

import fr.irit.smac.amasfactory.agent.IKnowledge;

public interface IHazelcastKnowledgeAccessor {

	void registerKnowledge(IKnowledge knowledge);

	void removeKnowledge(String knowledgeId);

	IKnowledge getKnowledge(String knowledgeId);

	Set<String> getKnowledgeIdSet();
	
	void shutdownInstance();

}