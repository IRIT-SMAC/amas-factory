package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl

import com.fasterxml.jackson.annotation.JsonProperty

import fr.irit.smac.amasfactory.agent.EExtraKnowledgeSkill;
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example1.EMyExtraKnowledgeSkill
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IExtraSkillCustom
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
        ((IExtraSkillCustom)this.skill.getExtraSkill().get(EMyExtraKnowledgeSkill.CUSTOM.getName())).increment()
//        this.skill.getExtraSkill().get(EExtraKnowledgeSkill.SOCIAL).getMsgBox().broadcast("hello")
    }

    @Override
    public void decideAndAct() {
        println "decideAndAct"
        ((IExtraSkillCustom)this.skill.getExtraSkill().get(EMyExtraKnowledgeSkill.CUSTOM.getName())).increment()
    }
}
