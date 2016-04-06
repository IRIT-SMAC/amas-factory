package fr.irit.smac.amasfactory.agent;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 
 * @author SVI
 * Minimal interface of the agent Knowledge use to store all the data of the agent.
 * Contains an ID.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IKnowledge {

	public String getId();
}
