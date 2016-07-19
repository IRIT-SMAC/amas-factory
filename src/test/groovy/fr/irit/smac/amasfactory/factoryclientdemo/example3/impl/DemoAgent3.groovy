package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.features.ICommonFeatures
import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial
import fr.irit.smac.amasfactory.agent.features.social.impl.KnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.impl.SkillSocial
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example3.ISkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent3 extends Agent<ICommonFeatures, IKnowledgeCustom, ISkillCustom<IKnowledgeCustom>> implements ITwoStepsAgent{

    public DemoAgent3() {
        super()
    }

    @Override
    public void perceive() {

        ISkillSocial<IKnowledgeSocial> skillSocial = commonFeatures.getFeatureSocial().getSkill()
        IKnowledgeSocial knowledgeSocial = commonFeatures.getFeatureSocial().getKnowledge()
        knowledgeSocial.getMsgBox().getMsgs().each {m -> skill.processMsg(skillSocial, m)}
    }

    @Override
    public void decideAndAct() {

        SkillSocial<KnowledgeSocial> skillSocial = commonFeatures.getFeatureSocial().getSkill()

        if (!knowledge.getSend()) {
            String id = commonFeatures.getFeatureBasic().getKnowledge().getId()
            commonFeatures.getFeatureSocial().getSkill().sendPortToTarget("ag1port", id)
            commonFeatures.getFeatureSocial().getSkill().sendPortToTarget("ag2port", id)
            knowledge.setSend(true)
        }
    }
}
