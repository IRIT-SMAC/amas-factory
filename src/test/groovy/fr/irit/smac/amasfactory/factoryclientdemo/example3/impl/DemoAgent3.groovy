package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.EExtraKnowledgeSkill
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.agent.social.IExtraSkillSocial
import fr.irit.smac.amasfactory.factoryclientdemo.example3.EMyExtraKnowledgeSkill
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IExtraKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IExtraSkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent3 extends Agent implements ITwoStepsAgent{

    public DemoAgent3() {
        super()
    }

    @Override
    public void perceive() {

        IExtraSkillCustom e = this.skill.getExtraSkills().get(EMyExtraKnowledgeSkill.CUSTOM.getName())
        e.processMessages()
    }

    @Override
    public void decideAndAct() {

        IExtraSkillSocial eSSocial = this.skill.getExtraSkills().get(EExtraKnowledgeSkill.SOCIAL.getName())
        eSSocial.updatePortFromMessage()

        IExtraKnowledgeCustom eKCustom = this.knowledge.getExtraKnowledges().get(EMyExtraKnowledgeSkill.CUSTOM.getName())
        
        if (!eKCustom.getSend()) {
            eSSocial.sendPort()
            eKCustom.setSend(true)
        }
    }
}
