package fr.irit.smac.amasfactory.factoryclientdemo

import org.slf4j.Logger

import com.fasterxml.jackson.annotation.JsonProperty

import fr.irit.smac.amasfactory.agent.impl.AbsInfrastructureAgent
import fr.irit.smac.libs.tooling.messaging.IMsgBox
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent extends AbsInfrastructureAgent<DemoMessage>implements ITwoStepsAgent{

    @JsonProperty
    private String               message
    private IMsgBox<DemoMessage> msgBox
    private Logger               logger

    public DemoAgent() {
        super()
    }

    @Override
    public void perceive() {
        for (DemoMessage demoMessage : msgBox.getMsgs()) {
            System.out.println(("agent " + this.getId() + " : I received " + demoMessage))
        }
    }

    @Override
    public void decideAndAct() {
        System.out.println("agent " + this.getId() + " : I send " + this.message)
        this.msgBox.broadcast(new DemoMessage(this, this.message))
    }

    @Override
    protected void initParameters() {

        this.msgBox = this.getInfra().getMessagingService().getMsgBox(this.getId())
        this.logger = this.getInfra().getLoggingService().getAgentLogger(this.getId())
    }
}
