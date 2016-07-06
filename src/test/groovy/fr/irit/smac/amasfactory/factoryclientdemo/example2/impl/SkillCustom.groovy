package fr.irit.smac.amasfactory.factoryclientdemo.example2.impl

import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.IPort
import fr.irit.smac.amasfactory.agent.features.social.impl.KnowledgeSocial
import fr.irit.smac.amasfactory.agent.impl.Skill
import fr.irit.smac.amasfactory.factoryclientdemo.example2.ISkillCustom
import fr.irit.smac.amasfactory.message.IMessage
import fr.irit.smac.amasfactory.message.Message
import fr.irit.smac.amasfactory.message.PortOfTargetMessage
import fr.irit.smac.amasfactory.message.ValuePortMessage
import fr.irit.smac.libs.tooling.messaging.IMsgBox

class SkillCustom<K extends KnowledgeCustom> extends Skill<K> implements ISkillCustom{

    @Override
    public void processMessages(KnowledgeSocial knowledgeSocial) {

        for (Message demoMessage : knowledgeSocial.getMsgBox().getMsgs()) {

            if (demoMessage instanceof ValuePortMessage) {
                knowledgeSocial.getValuePortMessageCollection().add(demoMessage)
            } else if (demoMessage instanceof PortOfTargetMessage) {
                knowledgeSocial.getPortOfTargetMessageCollection().add(demoMessage)
            }
        }
    }

    @Override
    public boolean checkPortMapFull(KnowledgeSocial knowledgeSocial) {

        boolean ok = true
        for (p in knowledgeSocial.getPortMap()) {
            IPort port = p.value
            if (port.getValue() == null) {
                ok = false
            }
        }
        return ok
    }

    @Override
    public void getOutputValue(KnowledgeSocial knowledgeSocial) {

        if (knowledgeSocial.getOutputValue() == null) {
            String val = ""
            for (p in knowledgeSocial.getPortMap()) {
                IPort port = p.value
                val += port.getValue()
            }

            knowledgeSocial.setOutputValue(val)
        }
    }
}
