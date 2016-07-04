package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl

import com.fasterxml.jackson.annotation.JsonProperty

import fr.irit.smac.amasfactory.agent.EFeature
import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example1.EMyFeature
import fr.irit.smac.amasfactory.factoryclientdemo.example1.ISkillCustom
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
        ((ISkillCustom) this.features.get(EMyFeature.CUSTOM.getName()).getSkill()).increment()
        ((IKnowledgeSocial) this.features.get(EFeature.SOCIAL.getName()).getKnowledge()).getMsgBox().broadcast("hello")
    }

    @Override
    public void decideAndAct() {
        println "decideAndAct"
        ((ISkillCustom) this.features.get(EMyFeature.CUSTOM.getName()).getSkill()).increment()
    }
}
