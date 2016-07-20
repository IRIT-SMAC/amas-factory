package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial
import fr.irit.smac.amasfactory.agent.impl.Skill
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example3.ISkillCustom
import fr.irit.smac.amasfactory.message.IMessage

public class SkillCustom<K extends IKnowledgeCustom> extends Skill implements ISkillCustom<K> {

    @Override
    public void processMsg(ISkillSocial<IKnowledgeSocial> skillSocial, IMessage message) {

        skillSocial.processMsg(message);
    }
}
