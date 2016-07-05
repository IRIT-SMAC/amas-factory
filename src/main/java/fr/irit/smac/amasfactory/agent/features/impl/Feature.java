package fr.irit.smac.amasfactory.agent.features.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;
import fr.irit.smac.amasfactory.agent.features.IFeature;

public class Feature<K extends IKnowledge,S extends ISkill<K>> implements IFeature<K,S>{

    @JsonProperty
    protected K knowledge;

    @JsonProperty
    protected S skill;
    
    public Feature() {
        
    }
    
    @Override
    public K getKnowledge() {
        return this.knowledge;
    }

    @Override
    public S getSkill() {
        return this.skill;
    }
    
    @JsonSetter("skill")
    public void setSkill(S skill){
        this.skill = skill;
        this.skill.setKnowledge(this.knowledge);
    }
}
