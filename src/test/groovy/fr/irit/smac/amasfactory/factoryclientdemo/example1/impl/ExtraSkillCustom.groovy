package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl

import fr.irit.smac.amasfactory.agent.IKnowledge
import fr.irit.smac.amasfactory.agent.impl.ExtraSkill
import fr.irit.smac.amasfactory.factoryclientdemo.example1.EMyExtraKnowledgeSkill;
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IExtraKnowledgeCustom;
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IExtraSkillCustom

class ExtraSkillCustom extends ExtraSkill implements IExtraSkillCustom{

    public void increment() {
        
        IExtraKnowledgeCustom e = this.knowledge.getExtraKnowledge().get(EMyExtraKnowledgeSkill.CUSTOM.getName())
        e.setCount(e.count +1)
    }
}
