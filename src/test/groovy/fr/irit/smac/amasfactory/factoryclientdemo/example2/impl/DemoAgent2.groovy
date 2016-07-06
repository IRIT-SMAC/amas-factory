package fr.irit.smac.amasfactory.factoryclientdemo.example2.impl

import fr.irit.smac.amasfactory.agent.features.IFeatures
import fr.irit.smac.amasfactory.agent.features.impl.Feature
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example2.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example2.ISkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent2<F extends IFeatures, K extends IKnowledgeCustom, S extends ISkillCustom, P extends Feature<K, S>> extends Agent<F,K,S,P> implements ITwoStepsAgent{

    public DemoAgent2() {
        super()
    }

    @Override
    public void perceive() {

        this.primaryFeature.getSkill().processMessages(this.commonFeatures.getFeatureSocial().getKnowledge())
    }

    @Override
    public void decideAndAct() {

        this.commonFeatures.getFeatureSocial().getSkill().updatePortFromMessage()

        boolean ok = this.primaryFeature.getSkill().checkPortMapFull(this.commonFeatures.getFeatureSocial().getKnowledge())

        if (ok && !this.primaryFeature.getKnowledge().getSend()) {
            this.primaryFeature.getSkill().getOutputValue(this.commonFeatures.getFeatureSocial().getKnowledge())
            this.commonFeatures.getFeatureSocial().getSkill().sendOutputValue()
            this.primaryFeature.getKnowledge().setSend(true)
        }
    }
}
