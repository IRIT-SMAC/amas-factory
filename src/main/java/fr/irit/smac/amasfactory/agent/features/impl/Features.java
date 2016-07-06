package fr.irit.smac.amasfactory.agent.features.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.features.IFeature;
import fr.irit.smac.amasfactory.agent.features.IFeatures;
import fr.irit.smac.amasfactory.agent.features.basic.IKnowledgeBasic;
import fr.irit.smac.amasfactory.agent.features.basic.ISkillBasic;
import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial;
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial;

public class Features implements IFeatures {

    @JsonProperty
    public IFeature<IKnowledgeSocial, ISkillSocial<IKnowledgeSocial>> featureSocial;

    @JsonProperty
    public IFeature<IKnowledgeBasic, ISkillBasic<IKnowledgeBasic>> featureBasic;

    public Features() {

    }

    @Override
    public IFeature<IKnowledgeSocial, ISkillSocial<IKnowledgeSocial>> getFeatureSocial() {

        return this.featureSocial;
    }

    @Override
    public IFeature<IKnowledgeBasic, ISkillBasic<IKnowledgeBasic>> getFeatureBasic() {

        return this.featureBasic;
    }
}
