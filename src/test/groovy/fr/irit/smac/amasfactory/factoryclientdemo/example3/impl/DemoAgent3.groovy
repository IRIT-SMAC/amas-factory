package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.features.ICommonFeatures
import fr.irit.smac.amasfactory.agent.features.social.impl.KnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.impl.SkillSocial
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example3.ISkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent3<F extends ICommonFeatures, K extends IKnowledgeCustom, S extends ISkillCustom> extends Agent<F,K,S> implements ITwoStepsAgent{

    public DemoAgent3() {
        super()
    }

    @Override
    public void perceive() {

        this.skill.processMessages(this.commonFeatures.getFeatureSocial().getKnowledge())
    }

    @Override
    public void decideAndAct() {

        SkillSocial<KnowledgeSocial> skillSocial = this.commonFeatures.getFeatureSocial().getSkill()
        skillSocial.updatePortFromMessage()

        if (!this.knowledge.getSend()) {
            skillSocial.sendPort(this.commonFeatures.getFeatureBasic().getKnowledge().getId())
            this.knowledge.setSend(true)
        }
    }
}
