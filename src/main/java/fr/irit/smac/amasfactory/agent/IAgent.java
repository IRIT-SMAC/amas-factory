package fr.irit.smac.amasfactory.agent;

import org.slf4j.Logger;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.agent.features.IFeatures;

/**
 * Interface that defines the interaction capabilities of an agent with the
 * infrastructure.
 *
 * @author lemouzy
 * @param <M>
 *            the generic type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IAgent<F extends IFeatures> {

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId();

    public void setId(String id);

    public void setLogger(Logger logger);
    
    public F getFeatures();
}
