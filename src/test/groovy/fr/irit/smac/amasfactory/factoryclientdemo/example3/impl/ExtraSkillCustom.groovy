package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.EFeature
import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial;
import fr.irit.smac.amasfactory.agent.impl.ExtraSkill
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IExtraSkillCustom
import fr.irit.smac.amasfactory.message.AbstractMessage
import fr.irit.smac.amasfactory.message.IMessage
import fr.irit.smac.amasfactory.message.PortOfTargetMessage
import fr.irit.smac.amasfactory.message.ValuePortMessage
import fr.irit.smac.libs.tooling.messaging.IMsgBox

public class ExtraSkillCustom extends ExtraSkill implements IExtraSkillCustom {

    @Override
    public void processMessages() {

        IKnowledgeSocial e = this.knowledge.getExtraKnowledges().get(EFeature.SOCIAL.getName())
        IMsgBox<IMessage> msgBox = e.getMsgBox()
        for (AbstractMessage demoMessage : msgBox.getMsgs()) {

            if (demoMessage instanceof ValuePortMessage) {
                e.getValuePortMessageCollection().add(demoMessage)
            } else if (demoMessage instanceof PortOfTargetMessage) {
                e.getPortOfTargetMessageCollection().add(demoMessage)
            }
        }
    }
}
