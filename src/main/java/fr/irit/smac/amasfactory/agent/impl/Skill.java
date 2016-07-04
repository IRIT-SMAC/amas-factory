package fr.irit.smac.amasfactory.agent.impl;

import fr.irit.smac.amasfactory.agent.ISkill;

public abstract class Skill<K> implements ISkill<K> {

    protected K knowledge;

    @Override
    public void setKnowledge(K knowledge) {
        this.knowledge = knowledge;
    }

}
