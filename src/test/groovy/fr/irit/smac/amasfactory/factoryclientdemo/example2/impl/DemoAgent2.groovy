package fr.irit.smac.amasfactory.factoryclientdemo.example2.impl

import fr.irit.smac.amasfactory.agent.features.ICommonFeatures
import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example2.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example2.ISkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent2 extends Agent<ICommonFeatures, IKnowledgeCustom, ISkillCustom<IKnowledgeCustom>> implements ITwoStepsAgent{

    public DemoAgent2() {
        super()
    }

    @Override
    public void perceive() {

        ISkillSocial<IKnowledgeSocial> skillSocial = commonFeatures.getFeatureSocial().getSkill()
        IKnowledgeSocial knowledgeSocial = commonFeatures.getFeatureSocial().getKnowledge()

        knowledgeSocial.getMsgBox().getMsgs().each {m ->
            skill.processMsg(skillSocial, m)
        }
    }

    @Override
    public void decideAndAct() {

        IKnowledgeSocial knowledgeSocial = commonFeatures.getFeatureSocial().getKnowledge()

        if (knowledge.getValue() == null) {
            knowledgeSocial.getPortMap().each { k,v -> knowledge.setValue(v.getValue().getAt(0))}
        }
        else if (!knowledge.getSend()) {
            String id = commonFeatures.getFeatureBasic().getKnowledge().getId()
            knowledgeSocial.getTargetMap().each { k,v ->
                commonFeatures.getFeatureSocial().getSkill().sendDataToTarget(v.getAgentId().concat(v.getPortTarget()), knowledge.getValue(), id)
            }
            knowledge.setSend(true)
        }
    }
}
