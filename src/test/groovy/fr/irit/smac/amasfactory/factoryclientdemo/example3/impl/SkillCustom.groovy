package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial
import fr.irit.smac.amasfactory.agent.impl.Skill
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example3.ISkillCustom
import fr.irit.smac.amasfactory.message.IMessage
import fr.irit.smac.amasfactory.message.Message
import fr.irit.smac.amasfactory.message.PortOfTargetMessage
import fr.irit.smac.amasfactory.message.ValuePortMessage
import fr.irit.smac.libs.tooling.messaging.IMsgBox

public class SkillCustom<K extends IKnowledgeCustom> extends Skill implements ISkillCustom<K> {

    @Override
    public void processMessages(IKnowledgeSocial knowledgeSocial) {

        IMsgBox<IMessage> msgBox = knowledgeSocial.getMsgBox()
        for (Message demoMessage : msgBox.getMsgs()) {

            if (demoMessage instanceof ValuePortMessage) {
                knowledgeSocial.getValuePortMessageCollection().add(demoMessage)
            } else if (demoMessage instanceof PortOfTargetMessage) {
                knowledgeSocial.getPortOfTargetMessageCollection().add(demoMessage)
            }
        }
    }
}
