package fr.irit.smac.amasfactory.agent.social;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Minimal interface of a target, i.e. a specific port of a given agent
 * 
 * @author lemouzy
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface ITarget {
    /**
     * @return the id of the target agent
     */
    public String getAgentId();

    /**
     * @return the id of the port of the target agent
     */
    public String getPortTarget();

    /**
     * @return the id of the port supposed to receive the value of the target
     *         agent
     */
    public String getPortSource();
}
