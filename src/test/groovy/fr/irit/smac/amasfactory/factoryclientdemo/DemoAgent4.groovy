package fr.irit.smac.amasfactory.factoryclientdemo

import fr.irit.smac.amasfactory.agent.IKnowledge
import fr.irit.smac.amasfactory.agent.social.impl.ExtraSkillSocial
import fr.irit.smac.amasfactory.message.AbstractMessage
import fr.irit.smac.amasfactory.message.PortOfTargetMessage
import fr.irit.smac.amasfactory.message.ValuePortMessage
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent4 extends ExtraSkillSocial implements ITwoStepsAgent{

    private boolean send = false

    public DemoAgent4() {
        super()
    }

    @Override
    public void perceive() {

        IKnowledge knowledge = this.getKnowledge()
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

        this.addTargetFromMessage()
        this.sendOutputValue()
    }
}
