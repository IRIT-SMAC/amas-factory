package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.features.IFeatures
import fr.irit.smac.amasfactory.agent.features.impl.Feature
import fr.irit.smac.amasfactory.agent.features.social.impl.KnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.impl.SkillSocial
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example3.ISkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent3<F extends IFeatures, K extends IKnowledgeCustom, S extends ISkillCustom, P extends Feature<K, S>> extends Agent<F,K,S,P> implements ITwoStepsAgent{

    public DemoAgent3() {
        super()
    }

    @Override
    public void perceive() {

        this.primaryFeature.getSkill().processMessages(this.commonFeatures.getFeatureSocial().getKnowledge())
    }

    @Override
    public void decideAndAct() {

        SkillSocial<KnowledgeSocial> skillSocial = this.commonFeatures.getFeatureSocial().getSkill()
        skillSocial.updatePortFromMessage()

        if (!this.primaryFeature.getKnowledge().getSend()) {
            skillSocial.sendPort(this.commonFeatures.getFeatureBasic().getKnowledge().getId())
            this.primaryFeature.getKnowledge().setSend(true)
        }
    }
}
