package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IFeature;
import fr.irit.smac.amasfactory.agent.impl.Features;
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IMyFeatures;

public class MyFeatures extends Features implements IMyFeatures{

    @JsonProperty
    public IFeature<KnowledgeCustom,SkillCustom> featureCustom;
    
    @Override
    public IFeature<KnowledgeCustom,SkillCustom> getFeatureCustom() {
        
        return this.featureCustom;
    }
}
