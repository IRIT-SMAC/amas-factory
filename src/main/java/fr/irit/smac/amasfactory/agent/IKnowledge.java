package fr.irit.smac.amasfactory.agent;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.message.PortOfTargetMessage;
import fr.irit.smac.amasfactory.message.ValuePortMessage;

/**
 * Minimal interface of the agent Knowledge uses to store all the data of the
 * agent. Contains an ID.
 * 
 * @author SVI
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IKnowledge {

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId();

    /**
     * @return a map containing the ports of an agent
     */
    public Map<String, IPort> getPortMap();

    public void setTargetSet(Set<ITarget> targetSet);
    
    /**
     * @return a map containing the targets of an agent
     */
    public Set<ITarget> getTargetSet();

    public Class<?> getOutputType();

    /**
     * @return the output value of the agent
     */
    public Object getOutputValue();

    /**
     * Sets the output value of the agent
     * 
     * @param outputValue
     *            the output value
     */
    public void setOutputValue(Object outputValue);

    public Collection<ValuePortMessage> getValuePortMessageCollection();

    public Collection<PortOfTargetMessage> getPortOfTargetMessageCollection();
}
