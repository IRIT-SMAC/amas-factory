package fr.irit.smac.amasfactory.factoryclientdemo.example2.impl

import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.IPort
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial
import fr.irit.smac.amasfactory.agent.impl.Skill
import fr.irit.smac.amasfactory.factoryclientdemo.example2.ISkillCustom
import fr.irit.smac.amasfactory.message.IMessage

class SkillCustom<K extends KnowledgeCustom> extends Skill<K> implements ISkillCustom<K>{

    @Override
    public void processMsg(ISkillSocial<IKnowledgeSocial> skillSocial, IMessage message) {

        skillSocial.processMsg(message);
    }
}
