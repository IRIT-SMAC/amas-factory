package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.features.ICommonFeatures
import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example3.ISkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent4 extends Agent<ICommonFeatures, IKnowledgeCustom, ISkillCustom<IKnowledgeCustom>> implements ITwoStepsAgent{

    private boolean send = false

    public DemoAgent4() {
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

        String id = commonFeatures.getFeatureBasic().getKnowledge().getId()
        commonFeatures.getFeatureSocial().getKnowledge().getTargetMap().each { k,v ->
            commonFeatures.getFeatureSocial().getSkill().sendDataToPortTarget(v.getAgentId().concat(v.getPortTarget()), knowledge.getValue(), id)
        }
    }
}
