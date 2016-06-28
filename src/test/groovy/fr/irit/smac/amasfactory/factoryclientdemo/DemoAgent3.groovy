package fr.irit.smac.amasfactory.factoryclientdemo

import fr.irit.smac.amasfactory.agent.IKnowledge
import fr.irit.smac.amasfactory.agent.impl.AbsInfrastructureAgent
import fr.irit.smac.amasfactory.message.AbstractMessage
import fr.irit.smac.amasfactory.message.PortOfTargetMessage
import fr.irit.smac.amasfactory.message.ValuePortMessage
import fr.irit.smac.libs.tooling.messaging.IMsgBox
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent3 extends AbsInfrastructureAgent<DemoMessage>implements ITwoStepsAgent{

    private boolean send = false

    private String valPort1 = null

    private String valPort2 = null

    public DemoAgent3() {
        super()
    }

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

        this.updatePortFromMessage()

        if (!send) {
            this.sendPort()
            send = true
        }
    }
}
