package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl

import com.fasterxml.jackson.annotation.JsonProperty

import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent<F extends MyFeatures> extends Agent<F> implements ITwoStepsAgent{

    @JsonProperty
    private String               message

    public DemoAgent() {
        super()
    }

    @Override
    public void perceive() {
        println "perceive"
        this.features.getFeatureCustom().getSkill().increment()
        this.features.getFeatureSocial().getKnowledge().getMsgBox().broadcast("hello")
    }

    @Override
    public void decideAndAct() {
        println "decideAndAct"
        this.features.getFeatureCustom().getSkill().increment()
    }
}
