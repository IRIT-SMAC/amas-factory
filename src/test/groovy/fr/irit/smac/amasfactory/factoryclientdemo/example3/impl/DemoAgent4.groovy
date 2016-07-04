package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.EExtraKnowledgeSkill
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.agent.social.IExtraSkillSocial
import fr.irit.smac.amasfactory.factoryclientdemo.example3.EMyExtraKnowledgeSkill
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IExtraSkillCustom
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class DemoAgent4 extends Agent implements ITwoStepsAgent{

    private boolean send = false

    public DemoAgent4() {
        super()
    }

    @Override
    public void perceive() {

        IExtraSkillCustom e = this.skill.getExtraSkill().get(EMyExtraKnowledgeSkill.CUSTOM.getName())
        e.processMessages()
    }

    @Override
    public void decideAndAct() {

        IExtraSkillSocial e = this.skill.getExtraSkill().get(EExtraKnowledgeSkill.SOCIAL.getName())
        e.addTargetFromMessage()
        e.sendOutputValue()
    }
}
