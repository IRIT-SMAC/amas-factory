package fr.irit.smac.amasfactory.factoryclientdemo.example3

import fr.irit.smac.amasfactory.agent.ISkill
import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial

public interface ISkillCustom<K extends IKnowledgeCustom> extends ISkill<K>{

    public void processMessages(IKnowledgeSocial knowledgeSocial)
}
