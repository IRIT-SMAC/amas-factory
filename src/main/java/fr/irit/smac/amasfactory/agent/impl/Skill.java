package fr.irit.smac.amasfactory.agent.impl;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IExtraSkill;
import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;

public class Skill implements ISkill {

    private IKnowledge knowledge;

    @JsonProperty
    protected Map<String,IExtraSkill> extraSkill;

    public Skill() {

    }

    @Override
    public void setKnowledge(IKnowledge knowledge) {
        this.knowledge = knowledge;
        this.extraSkill.forEach((s, e) -> e.setKnowledge(this.knowledge));
    }
    
    public Map<String, IExtraSkill> getExtraSkill() {
        return extraSkill;
    }
}
