package fr.irit.smac.amasfactory.factoryclientdemo.example2.impl

import fr.irit.smac.amasfactory.agent.EExtraKnowledgeSkill
import fr.irit.smac.amasfactory.agent.impl.ExtraSkill
import fr.irit.smac.amasfactory.agent.social.IExtraKnowledgeSocial
import fr.irit.smac.amasfactory.agent.social.IExtraSkillSocial
import fr.irit.smac.amasfactory.agent.social.IPort
import fr.irit.smac.amasfactory.factoryclientdemo.example2.IExtraSkillCustom
import fr.irit.smac.amasfactory.message.AbstractMessage
import fr.irit.smac.amasfactory.message.IMessage
import fr.irit.smac.amasfactory.message.PortOfTargetMessage
import fr.irit.smac.amasfactory.message.ValuePortMessage
import fr.irit.smac.libs.tooling.messaging.IMsgBox

class ExtraSkillCustom extends ExtraSkill implements IExtraSkillCustom{

    public void processMessages() {

        IExtraKnowledgeSocial e = this.knowledge.getExtraKnowledges().get(EExtraKnowledgeSkill.SOCIAL.getName())
        IMsgBox<IMessage> msgBox = e.getMsgBox()
        for (AbstractMessage demoMessage : msgBox.getMsgs()) {

            if (demoMessage instanceof ValuePortMessage) {
                e.getValuePortMessageCollection().add(demoMessage)
            } else if (demoMessage instanceof PortOfTargetMessage) {
                e.getPortOfTargetMessageCollection().add(demoMessage)
            }
        }
    }

    @Override
    public boolean checkPortMapFull() {

        IExtraKnowledgeSocial e = this.knowledge.getExtraKnowledges().get(EExtraKnowledgeSkill.SOCIAL.getName())

        boolean ok = true
        for (p in e.getPortMap()) {
            IPort port = p.value
            if (port.getValue() == null) {
                ok = false
            }
        }
        return ok
    }

    @Override
    public void getOutputValue() {

        IExtraKnowledgeSocial e = this.knowledge.getExtraKnowledges().get(EExtraKnowledgeSkill.SOCIAL.getName())

        if (e.getOutputValue() == null) {
            String val = ""
            for (p in e.getPortMap()) {
                IPort port = p.value
                val += port.getValue()
            }

            e.setOutputValue(val)
        }
    }
}
