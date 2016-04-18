package fr.irit.smac.amasfactory.agent;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Minimal interface of the agent Knowledge uses to store all the data of the
 * agent. Contains an ID.
 * 
 * @author SVI
 */
@FunctionalInterface
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IKnowledge {

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId();
}
