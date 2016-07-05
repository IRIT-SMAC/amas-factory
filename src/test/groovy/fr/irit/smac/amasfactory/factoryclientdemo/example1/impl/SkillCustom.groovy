package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl

import fr.irit.smac.amasfactory.agent.impl.Skill
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example1.ISkillCustom

class SkillCustom<K extends KnowledgeCustom> extends Skill<K> implements ISkillCustom{

    public SkillCustom() {
    }

    public void increment() {

        this.knowledge.setCount(this.knowledge.count +1)
    }
}
