package fr.irit.smac.amasfactory.factoryclientdemo

import org.slf4j.Logger

import com.fasterxml.jackson.annotation.JsonProperty

import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.agent.social.impl.AgentSocial
import fr.irit.smac.libs.tooling.messaging.IMsgBox
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent extends Agent implements ITwoStepsAgent{

    @JsonProperty
    private String               message

    public DemoAgent() {
        super()
    }

    @Override
    public void perceive() {
        println "perceive"
        this.knowledge.increment()
    }

    @Override
    public void decideAndAct() {
        println "decideAndAct"
        this.knowledge.increment()
    }

}
