package fr.irit.smac.amasfactory.factoryclientdemo

import fr.irit.smac.amasfactory.agent.social.IExtraKnowledgeSocial
import fr.irit.smac.amasfactory.agent.social.IPort
import fr.irit.smac.amasfactory.agent.social.impl.ExtraSkillSocial
import fr.irit.smac.amasfactory.message.AbstractMessage
import fr.irit.smac.amasfactory.message.PortOfTargetMessage
import fr.irit.smac.amasfactory.message.ValuePortMessage
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent2 extends ExtraSkillSocial implements ITwoStepsAgent{

    private boolean send = false

    public DemoAgent2() {
        super()
    }

    @Override
    public void perceive() {

        IExtraKnowledgeSocial knowledge = this.getKnowledge()
        for (AbstractMessage demoMessage : msgBox.getMsgs()) {

            if (demoMessage instanceof ValuePortMessage) {
                knowledge.getValuePortMessageCollection().add(demoMessage)
            } else if (demoMessage instanceof PortOfTargetMessage) {
                knowledge.getPortOfTargetMessageCollection().add(demoMessage)
            }
        }
    }

    @Override
    public void decideAndAct() {

        this.updatePortFromMessage()

        boolean ok = true
        for (e in this.getKnowledge().getPortMap()) {
            IPort port = e.value
            if (port.getValue() == null) {
                ok = false
            }
        }

        if (ok && !send) {

            if (this.getKnowledge().getOutputValue() == null) {
                String val = ""
                for (e in this.getKnowledge().getPortMap()) {
                    IPort port = e.value
                    val += port.getValue()
                }

                this.getKnowledge().setOutputValue(val)
            }
            this.sendOutputValue()
            send = true
        }
    }
}
