package fr.irit.smac.amasfactory.factoryclientdemo

import fr.irit.smac.amasfactory.agent.IKnowledge
import fr.irit.smac.amasfactory.agent.impl.AbsInfrastructureAgent
import fr.irit.smac.amasfactory.message.AbstractMessage
import fr.irit.smac.amasfactory.message.PortOfTargetMessage
import fr.irit.smac.amasfactory.message.ValuePortMessage
import fr.irit.smac.libs.tooling.messaging.IMsgBox
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent4 extends AbsInfrastructureAgent<DemoMessage>implements ITwoStepsAgent{

    public DemoAgent4() {
        super()
    }

    private boolean send = false

    @Override
    public void perceive() {

        IKnowledge knowledge = this.getInnerKnowledge()
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
