package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.features.ICommonFeatures
import fr.irit.smac.amasfactory.agent.features.impl.Feature
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent4<F extends ICommonFeatures, K extends KnowledgeCustom, S extends SkillCustom<K>, P extends Feature<K, S>> extends Agent<F,K,S,P> implements ITwoStepsAgent{

    private boolean send = false

    public DemoAgent4() {
        super()
    }

    @Override
    public void perceive() {

        this.primaryFeature.getSkill().processMessages(this.commonFeatures.getFeatureSocial().getKnowledge())
    }

    @Override
    public void decideAndAct() {

        ISkillSocial skillSocial = this.commonFeatures.getFeatureSocial().getSkill()
        skillSocial.addTargetFromMessage()
        skillSocial.sendOutputValue()
    }
}
