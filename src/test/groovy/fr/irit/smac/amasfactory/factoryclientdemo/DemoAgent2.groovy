package fr.irit.smac.amasfactory.factoryclientdemo

import java.util.Map

import org.slf4j.Logger

import com.fasterxml.jackson.annotation.JsonProperty

import fr.irit.smac.amasfactory.agent.IPort
import fr.irit.smac.amasfactory.agent.impl.AbsInfrastructureAgent
import fr.irit.smac.amasfactory.message.SendToTargetMessage
import fr.irit.smac.libs.tooling.messaging.IMsgBox
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent2 extends AbsInfrastructureAgent<DemoMessage>implements ITwoStepsAgent{

    public DemoAgent2() {
        super()
    }

    private boolean send = false

    @Override
    public void perceive() {


        for (SendToTargetMessage demoMessage : msgBox.getMsgs()) {
            System.out.println(("agent " + this.getId() + " : I received " + demoMessage))
            logger.debug("agent " + this.getId() + " : I received " + demoMessage)


            logger.info("updating port "+demoMessage.getPortTarget()+" with "+demoMessage.getValue())
            IPort p = this.getInnerKnowledge().getPortMap().get(demoMessage.getPortTarget())
            p.setValue(demoMessage.getValue())
        }
    }

    @Override
    public void decideAndAct() {

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
            this.sendOutputToTargets()
            send = true
        }
    }
}
