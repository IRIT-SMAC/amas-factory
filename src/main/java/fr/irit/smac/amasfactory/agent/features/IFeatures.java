package fr.irit.smac.amasfactory.agent.features;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.agent.IFeature;
import fr.irit.smac.amasfactory.agent.features.basic.impl.KnowledgeBasic;
import fr.irit.smac.amasfactory.agent.features.basic.impl.SkillBasic;
import fr.irit.smac.amasfactory.agent.features.social.impl.KnowledgeSocial;
import fr.irit.smac.amasfactory.agent.features.social.impl.SkillSocial;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IFeatures {

    IFeature<KnowledgeSocial, SkillSocial<KnowledgeSocial>> getFeatureSocial();

    IFeature<KnowledgeBasic, SkillBasic<KnowledgeBasic>> getFeatureBasic();

}
