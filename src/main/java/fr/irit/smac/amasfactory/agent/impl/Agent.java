package fr.irit.smac.amasfactory.agent.impl;

import org.slf4j.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;
import fr.irit.smac.amasfactory.agent.features.IFeature;
import fr.irit.smac.amasfactory.agent.features.IFeatures;

/**
 * Abstract class used by subclasses implementing an agent
 *
 * @param <M>
 *            the generic type
 */
public class Agent<F extends IFeatures, K extends IKnowledge, S extends ISkill<K>, P extends IFeature<K, S>> implements IAgent<F, K, S, P> {

    @JsonProperty
    protected String id;

    @JsonProperty
    protected F commonFeatures;
    
    @JsonProperty
    protected P primaryFeature;
    
    protected Logger logger;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public F getFeatures() {
        return this.commonFeatures;
    }

    @Override
    public P getPrimaryFeature() {
        return this.primaryFeature;
    }

}
