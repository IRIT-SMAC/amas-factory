package fr.irit.smac.amasfactory.agent.impl;

import fr.irit.smac.amasfactory.agent.IExtraSkill;
import fr.irit.smac.amasfactory.agent.IKnowledge;

public abstract class ExtraSkill implements IExtraSkill{

    protected IKnowledge knowledge;
    
    public void setKnowledge(IKnowledge knowledge) {
        this.knowledge = knowledge;
    }

}
