package fr.irit.smac.amasfactory.agent.impl;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IExtraSkill;
import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;

public class Skill implements ISkill {

    private IKnowledge knowledge;

    @JsonProperty
    protected Map<String,IExtraSkill> extraSkills;

    public Skill() {

    }

    @Override
    public void setKnowledge(IKnowledge knowledge) {
        this.knowledge = knowledge;
        this.extraSkills.forEach((s, e) -> e.setKnowledge(this.knowledge));
    }
    
    public Map<String, IExtraSkill> getExtraSkills() {
        return extraSkills;
    }
}
