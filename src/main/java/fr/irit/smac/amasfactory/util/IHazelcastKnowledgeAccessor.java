package fr.irit.smac.amasfactory.util;

import java.util.Map;
import java.util.Set;

import fr.irit.smac.amasfactory.agent.features.basic.IKnowledgeBasic;

public interface IHazelcastKnowledgeAccessor {

    void registerKnowledge(IKnowledgeBasic knowledge);

    void removeKnowledge(String knowledgeId);

    IKnowledgeBasic getKnowledge(String knowledgeId);

    Set<String> getKnowledgeIdSet();

    Map<String, IKnowledgeBasic> getKnowledgeMap();

    void shutdownInstance();

}