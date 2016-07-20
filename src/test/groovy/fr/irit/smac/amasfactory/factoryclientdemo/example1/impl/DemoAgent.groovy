package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl;

import fr.irit.smac.amasfactory.agent.features.ICommonFeatures
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example1.ISkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent extends Agent<ICommonFeatures, IKnowledgeCustom, ISkillCustom<IKnowledgeCustom>> implements ITwoStepsAgent {

    public DemoAgent() {
        super()
    }
    
    public DemoAgent(String id) {
        super(id)
    }

    @Override
    public void perceive() {
        println "perceive"
        this.skill.increment()
    }

    @Override
    public void decideAndAct() {
        println "decideAndAct"
        this.skill.increment()
    }
}
