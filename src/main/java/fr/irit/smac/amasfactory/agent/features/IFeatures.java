package fr.irit.smac.amasfactory.agent.features;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.agent.features.basic.IKnowledgeBasic;
import fr.irit.smac.amasfactory.agent.features.basic.ISkillBasic;
import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial;
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IFeatures {

    IFeature<IKnowledgeSocial, ISkillSocial<IKnowledgeSocial>> getFeatureSocial();

    IFeature<IKnowledgeBasic, ISkillBasic<IKnowledgeBasic>> getFeatureBasic();

}
