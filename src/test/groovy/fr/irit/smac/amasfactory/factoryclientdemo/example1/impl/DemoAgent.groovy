package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl;

import fr.irit.smac.amasfactory.agent.features.IFeature
import fr.irit.smac.amasfactory.agent.features.ICommonFeatures
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example1.ISkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent<F extends ICommonFeatures, K extends IKnowledgeCustom, S extends ISkillCustom, P extends IFeature<K, S>>
    extends Agent<F, K, S, P>implements ITwoStepsAgent {

    public DemoAgent() {
        super()
    }
    
    public DemoAgent(String id) {
        super(id)
    }

    @Override
    public void perceive() {
        println "perceive"
        this.primaryFeature.getSkill().increment()
    }

    @Override
    public void decideAndAct() {
        println "decideAndAct"
        this.primaryFeature.getSkill().increment()
    }
}
