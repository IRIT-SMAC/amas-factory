package fr.irit.smac.amasfactory.agent.social;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.message.PortOfTargetMessage;
import fr.irit.smac.amasfactory.message.ValuePortMessage;

public interface IKnowledgeSocial extends IKnowledge {

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
