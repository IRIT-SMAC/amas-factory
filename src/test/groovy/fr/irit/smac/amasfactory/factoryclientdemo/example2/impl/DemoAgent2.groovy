package fr.irit.smac.amasfactory.factoryclientdemo.example2.impl

import fr.irit.smac.amasfactory.agent.features.ICommonFeatures
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example2.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example2.ISkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent2<F extends ICommonFeatures, K extends IKnowledgeCustom, S extends ISkillCustom> extends Agent<F,K,S> implements ITwoStepsAgent{

    public DemoAgent2() {
        super()
    }

    @Override
    public void perceive() {

        this.getSkill().processMessages(this.commonFeatures.getFeatureSocial().getKnowledge())
    }

    @Override
    public void decideAndAct() {

        this.commonFeatures.getFeatureSocial().getSkill().updatePortFromMessage()

        boolean ok = this.getSkill().checkPortMapFull(this.commonFeatures.getFeatureSocial().getKnowledge())

        if (ok && !this.knowledge.getSend()) {
            this.skill.getOutputValue(this.commonFeatures.getFeatureSocial().getKnowledge())
            this.commonFeatures.getFeatureSocial().getSkill().sendOutputValue()
            this.knowledge.setSend(true)
        }
    }
}
