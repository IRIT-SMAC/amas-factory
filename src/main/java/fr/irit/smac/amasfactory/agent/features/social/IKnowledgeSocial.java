package fr.irit.smac.amasfactory.agent.features.social;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.amasfactory.message.PortOfTargetMessage;
import fr.irit.smac.amasfactory.message.ValuePortMessage;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;

public interface IKnowledgeSocial extends IKnowledge {

    /**
     * @return a map containing the ports of an agent
     */
    public Map<String, IPort> getPortMap();

    public void setTargetSet(Map<String,ITarget> targetSet);

    /**
     * @return a map containing the targets of an agent
     */
    public Map<String, ITarget> getTargetMap();

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

    public IMsgBox<IMessage> getMsgBox();

    public void setMsgBox(IMsgBox<IMessage> msgBox);
}
