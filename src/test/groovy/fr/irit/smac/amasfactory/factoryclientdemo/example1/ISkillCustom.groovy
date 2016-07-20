package fr.irit.smac.amasfactory.factoryclientdemo.example1

import fr.irit.smac.amasfactory.agent.ISkill


interface ISkillCustom<K extends IKnowledgeCustom> extends ISkill<K>{

    public void increment()
}
