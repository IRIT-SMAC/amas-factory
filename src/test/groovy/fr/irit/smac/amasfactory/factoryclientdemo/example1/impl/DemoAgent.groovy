package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl;

import fr.irit.smac.amasfactory.agent.features.IFeatures
import fr.irit.smac.amasfactory.agent.features.impl.Feature
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example1.ISkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent<F extends IFeatures, K extends IKnowledgeCustom, S extends ISkillCustom, P extends Feature<K, S>>
    extends Agent<F, K, S, P>implements ITwoStepsAgent {

    public DemoAgent() {
        super();
    }

    @Override
    public void perceive() {
        println "perceive"
        this.primaryFeature.getSkill().increment();
    }

    @Override
    public void decideAndAct() {
        println "decideAndAct"
        this.primaryFeature.getSkill().increment();
    }
}
