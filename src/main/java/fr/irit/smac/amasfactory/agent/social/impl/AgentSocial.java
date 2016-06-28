package fr.irit.smac.amasfactory.agent.social.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import fr.irit.smac.amasfactory.agent.impl.Agent;
import fr.irit.smac.amasfactory.agent.social.IAgentSocial;
import fr.irit.smac.amasfactory.agent.social.IKnowledgeSocial;
import fr.irit.smac.amasfactory.agent.social.IPort;
import fr.irit.smac.amasfactory.agent.social.ITarget;
import fr.irit.smac.amasfactory.message.PortOfTargetMessage;
import fr.irit.smac.amasfactory.message.ValuePortMessage;

public class AgentSocial<M> extends Agent<M> implements IAgentSocial{
    
    protected AgentSocial() {
        super();
    }

    @Override
    public void sendOutputValue() {

        Object value = ((IKnowledgeSocial) this.knowledge).getOutputValue();

        for (ITarget target : ((IKnowledgeSocial) this.knowledge).getTargetSet()) {
            String agentId = target.getAgentId();
            String port = target.getPortTarget();
            // logger.info("send to target " + target.getAgentId() + "
            // message= " + value);
            this.msgBox.send((M)
                 new ValuePortMessage(port, value),
                agentId);
        }
    }

    @Override
    public void sendPort() {

        Set<ITarget> targets = ((IKnowledgeSocial) this.knowledge).getTargetSet();

        for (ITarget target : targets) {
            String agentId = target.getAgentId();
            String portTarget = target.getPortTarget();
            String portSource = target.getPortSource();
            this.msgBox.send((M) new PortOfTargetMessage(portTarget, portSource, this.getId()),
                agentId);
        }
    }

    @Override
    public void addTargetFromMessage() {

        Collection<PortOfTargetMessage> portOfTargetsMessageCollection = ((IKnowledgeSocial) this.knowledge).getPortOfTargetMessageCollection();

        for (PortOfTargetMessage message : portOfTargetsMessageCollection) {
            Set<ITarget> targetSet = new HashSet<ITarget>();
            ITarget target = new Target(message.getValue().toString(), message.getPortSource(),
                message.getPortTarget());
            targetSet.add(target);
            ((IKnowledgeSocial) this.knowledge).setTargetSet(targetSet);
        }
    }

    @Override
    public void updatePortFromMessage() {

        Collection<ValuePortMessage> valuePortMessageCollection = ((IKnowledgeSocial) this.knowledge).getValuePortMessageCollection();

        for (ValuePortMessage message : valuePortMessageCollection) {
            IPort p = ((IKnowledgeSocial) this.knowledge).getPortMap().get(message.getPort());
            p.setValue(message.getValue());
        }
    }
}
