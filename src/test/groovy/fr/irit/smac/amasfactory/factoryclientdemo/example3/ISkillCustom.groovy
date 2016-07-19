package fr.irit.smac.amasfactory.factoryclientdemo.example3

import fr.irit.smac.amasfactory.agent.ISkill
import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial
import fr.irit.smac.amasfactory.message.IMessage

public interface ISkillCustom<K extends IKnowledgeCustom> extends ISkill<K>{

    public void processMsg(ISkillSocial<IKnowledgeSocial> skillSocial, IMessage message)
}
