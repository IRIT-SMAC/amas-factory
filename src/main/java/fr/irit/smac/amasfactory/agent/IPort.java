package fr.irit.smac.amasfactory.agent;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Minimal interface of an input agent port
 * 
 * @author lemouzy
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IPort {

    /**
     * @return the id of the port
     */
    public String getId();

    /**
     * @return the class that represent the type of the values that can be set
     *         to that port
     */
    public Class<?> getType();

    /**
     * @return the value of the port
     */
    public Object getValue();

    /**
     * Sets the value of the port
     * 
     * @param value
     *            the new value of the port
     */
    public void setValue(Object value);
}
