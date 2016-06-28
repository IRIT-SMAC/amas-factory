package fr.irit.smac.amasfactory.factoryclientdemo

import fr.irit.smac.amasfactory.agent.IKnowledge
import fr.irit.smac.amasfactory.agent.IPort
import fr.irit.smac.amasfactory.agent.impl.AbsInfrastructureAgent
import fr.irit.smac.amasfactory.message.AbstractMessage
import fr.irit.smac.amasfactory.message.PortOfTargetMessage
import fr.irit.smac.amasfactory.message.ValuePortMessage
import fr.irit.smac.libs.tooling.messaging.IMsgBox
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent2 extends AbsInfrastructureAgent<DemoMessage>implements ITwoStepsAgent{

    private boolean send = false

    public DemoAgent2() {
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

        boolean ok = true
        for (e in this.getInnerKnowledge().getPortMap()) {
            IPort port = e.value
            if (port.getValue() == null) {
                ok = false
            }
        }

        if (ok && !send) {

            if (this.getInnerKnowledge().getOutputValue() == null) {
                String val = ""
                for (e in this.getInnerKnowledge().getPortMap()) {
                    IPort port = e.value
                    val += port.getValue()
                }

                this.getInnerKnowledge().setOutputValue(val)
            }
            this.sendOutputValue()
            send = true
        }
    }
}
