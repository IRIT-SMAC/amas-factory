package fr.irit.smac.amasfactory.agent.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import fr.irit.smac.amasfactory.agent.IFeature;
import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;

public class Feature implements IFeature{

    @JsonProperty
    protected IKnowledge knowledge;

    @JsonProperty
    protected ISkill skill;
    
    protected String id;
    
    public Feature() {
        
    }
    
    @Override
    public IKnowledge getKnowledge() {
        return this.knowledge;
    }

    @Override
    public ISkill getSkill() {
        return this.skill;
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
    @JsonSetter("skill")
    public void setSkill(ISkill skill){
        this.skill = skill;
        this.skill.setKnowledge(this.knowledge);
    }
}
