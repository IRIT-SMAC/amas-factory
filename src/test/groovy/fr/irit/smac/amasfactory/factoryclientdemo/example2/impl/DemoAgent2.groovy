package fr.irit.smac.amasfactory.factoryclientdemo.example2.impl

import fr.irit.smac.amasfactory.agent.EExtraKnowledgeSkill
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.agent.social.IExtraSkillSocial
import fr.irit.smac.amasfactory.factoryclientdemo.example2.EMyExtraKnowledgeSkill
import fr.irit.smac.amasfactory.factoryclientdemo.example2.IExtraKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example2.IExtraSkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent2 extends Agent implements ITwoStepsAgent{

    public DemoAgent2() {
        super()
    }

    @Override
    public void perceive() {

        IExtraSkillCustom e = this.skill.getExtraSkill().get(EMyExtraKnowledgeSkill.CUSTOM.getName())
        e.processMessages()
    }

    @Override
    public void decideAndAct() {

        IExtraSkillSocial eSocial = this.skill.getExtraSkill().get(EExtraKnowledgeSkill.SOCIAL.getName())
        eSocial.updatePortFromMessage()

        IExtraSkillCustom eSCustom = this.skill.getExtraSkill().get(EMyExtraKnowledgeSkill.CUSTOM.getName())
        boolean ok = eSCustom.checkPortMapFull()

        IExtraKnowledgeCustom eKCustom = this.knowledge.getExtraKnowledge().get(EMyExtraKnowledgeSkill.CUSTOM.getName())
        
        if (ok && !eKCustom.getSend()) {
            eSCustom.getOutputValue()
            IExtraSkillSocial eSkillCustom = this.skill.getExtraSkill().get(EExtraKnowledgeSkill.SOCIAL.getName())
            eSkillCustom.sendOutputValue()
            eKCustom.setSend(true)
        }
    }
}
