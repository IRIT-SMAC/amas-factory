package fr.irit.smac.amasfactory.agent.impl;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;

public abstract class Skill implements ISkill {

    protected IKnowledge knowledge;

    @Override
    public void setKnowledge(IKnowledge knowledge) {
        this.knowledge = knowledge;
    }

}
