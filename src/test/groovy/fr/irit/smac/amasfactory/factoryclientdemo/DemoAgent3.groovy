package fr.irit.smac.amasfactory.factoryclientdemo

import java.util.Map

import org.slf4j.Logger

import com.fasterxml.jackson.annotation.JsonProperty

import fr.irit.smac.amasfactory.agent.IPort
import fr.irit.smac.amasfactory.agent.ITarget
import fr.irit.smac.amasfactory.agent.impl.AbsInfrastructureAgent
import fr.irit.smac.amasfactory.message.SendToTargetMessage
import fr.irit.smac.libs.tooling.messaging.IMsgBox
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent3 extends AbsInfrastructureAgent<DemoMessage>implements ITwoStepsAgent{

    public DemoAgent3() {
        super()
    }

    private boolean send = false

    private String valPort1 = null

    private String valPort2 = null

    @Override
    public void perceive() {


        for (SendToTargetMessage demoMessage : msgBox.getMsgs()) {
            System.out.println(("agent " + this.getId() + " : I received " + demoMessage))

            IPort p = this.getInnerKnowledge().getPortMap().get(demoMessage.getPortTarget())
            p.setValue(demoMessage.getValue())
        }
    }

    @Override
    public void decideAndAct() {

        if (!send) {
            this.sendPortSourceToTargets()
            send = true
        }
    }

    private void sendPortSourceToTargets() {

        if (this.getInnerKnowledge().getTargetSet() != null) {
            for (ITarget target : this.getInnerKnowledge().getTargetSet()) {

                String agentId = target.getAgentId()
                String portTarget = target.getPortTarget()
                String portSource = target.getPortSource()
                this.msgBox.send(
                                new SendToTargetMessage(portTarget, portSource, this.getId()),
                                agentId)
            }
        }
    }
}
