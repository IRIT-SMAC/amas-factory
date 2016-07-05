package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.features.IFeature;
import fr.irit.smac.amasfactory.agent.features.impl.Features;
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IMyFeatures;

public class MyFeatures extends Features implements IMyFeatures{

    @JsonProperty
    public IFeature<KnowledgeCustom,SkillCustom<KnowledgeCustom>> featureCustom;
    
    @Override
    public IFeature<KnowledgeCustom,SkillCustom<KnowledgeCustom>> getFeatureCustom() {
        
        return this.featureCustom;
    }
}
