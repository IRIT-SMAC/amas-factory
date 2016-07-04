package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.EFeature
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial;
import fr.irit.smac.amasfactory.agent.impl.Agent
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

        IExtraSkillCustom e = this.skill.getExtraSkills().get(EMyExtraKnowledgeSkill.CUSTOM.getName())
        e.processMessages()
    }

    @Override
    public void decideAndAct() {

        ISkillSocial e = this.skill.getExtraSkills().get(EFeature.SOCIAL.getName())
        e.addTargetFromMessage()
        e.sendOutputValue()
    }
}
