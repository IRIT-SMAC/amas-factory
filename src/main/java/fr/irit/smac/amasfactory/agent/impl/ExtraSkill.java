package fr.irit.smac.amasfactory.agent.impl;

import java.io.Serializable;

import fr.irit.smac.amasfactory.agent.IExtraSkill;
import fr.irit.smac.amasfactory.agent.IKnowledge;

public abstract class ExtraSkill implements IExtraSkill, Serializable{

    private static final long serialVersionUID = 4834838667745994673L;
    protected IKnowledge knowledge;
    
    public void setKnowledge(IKnowledge knowledge) {
        this.knowledge = knowledge;
    }

}
