package fr.irit.smac.amasfactory.agent.features.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.features.IFeature;
import fr.irit.smac.amasfactory.agent.features.IFeatures;
import fr.irit.smac.amasfactory.agent.features.basic.impl.KnowledgeBasic;
import fr.irit.smac.amasfactory.agent.features.basic.impl.SkillBasic;
import fr.irit.smac.amasfactory.agent.features.social.impl.KnowledgeSocial;
import fr.irit.smac.amasfactory.agent.features.social.impl.SkillSocial;

public class Features implements IFeatures {

    @JsonProperty
    public IFeature<KnowledgeSocial, SkillSocial<KnowledgeSocial>> featureSocial;

    @JsonProperty
    public IFeature<KnowledgeBasic, SkillBasic<KnowledgeBasic>> featureBasic;

    public Features() {

    }

    @Override
    public IFeature<KnowledgeSocial, SkillSocial<KnowledgeSocial>> getFeatureSocial() {

        return this.featureSocial;
    }

    @Override
    public IFeature<KnowledgeBasic, SkillBasic<KnowledgeBasic>> getFeatureBasic() {

        return this.featureBasic;
    }
}
