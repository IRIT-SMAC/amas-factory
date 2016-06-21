package fr.irit.smac.amasfactory.factoryclientdemo

import org.slf4j.Logger

import fr.irit.smac.amasfactory.agent.ITarget
import fr.irit.smac.amasfactory.agent.impl.AbsInfrastructureAgent
import fr.irit.smac.amasfactory.agent.impl.Target
import fr.irit.smac.amasfactory.message.SendToTargetMessage
import fr.irit.smac.libs.tooling.messaging.IMsgBox
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent4 extends AbsInfrastructureAgent<DemoMessage>implements ITwoStepsAgent{

    public DemoAgent4() {
        super()
    }

    private boolean send = false

    @Override
    public void perceive() {


        for (SendToTargetMessage demoMessage : msgBox.getMsgs()) {
            System.out.println(("agent " + this.getId() + " : I received " + demoMessage))
            logger.debug("agent " + this.getId() + " : I received " + demoMessage)


            logger.info("updating port "+demoMessage.getPortTarget()+" with "+demoMessage.getValue())
            Set<ITarget> targetSet = new HashSet<ITarget>()
            ITarget target = new Target(demoMessage.getValue(), demoMessage.getPortSource(), demoMessage.getPortTarget())
            targetSet.add(target)
            this.getInnerKnowledge().setTargetSet(targetSet)
        }
    }

    @Override
    public void decideAndAct() {

        for (ITarget target : this.getInnerKnowledge().getTargetSet()) {

            String agentId = target.getAgentId()
            String portTarget = target.getPortTarget()
            String portSource = target.getPortSource()
            // logger.info("send to target " + target.getAgentId() + "
            // message= " + value);
            this.msgBox.send(
                            new SendToTargetMessage(portTarget, portSource,this.getInnerKnowledge().getOutputValue()), agentId)
        }
    }
}
