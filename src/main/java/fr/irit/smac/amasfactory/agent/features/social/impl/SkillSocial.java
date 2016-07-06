package fr.irit.smac.amasfactory.agent.features.social.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial;
import fr.irit.smac.amasfactory.agent.features.social.IPort;
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial;
import fr.irit.smac.amasfactory.agent.features.social.ITarget;
import fr.irit.smac.amasfactory.agent.impl.Skill;
import fr.irit.smac.amasfactory.message.PortOfTargetMessage;
import fr.irit.smac.amasfactory.message.ValuePortMessage;

public class SkillSocial<K extends KnowledgeSocial> extends Skill<K> implements ISkillSocial {

    public SkillSocial() {

    }

    @Override
    public void sendOutputValue(String id) {

        IKnowledgeSocial e = this.knowledge;
        Object value = e.getOutputValue();

        for (ITarget target : e.getTargetSet()) {
            String agentId = target.getAgentId();
            String port = target.getPortTarget();
            // logger.info("send to target " + target.getAgentId() + "
            // message= " + value);
            e.getMsgBox().send(
                new ValuePortMessage(port, value, id),
                agentId);
        }
    }

    @Override
    public void sendPort(String id) {

        IKnowledgeSocial e = this.knowledge;

        Set<ITarget> targets = e.getTargetSet();

        for (ITarget target : targets) {
            String agentId = target.getAgentId();
            String portTarget = target.getPortTarget();
            String portSource = target.getPortSource();
            e.getMsgBox().send(new PortOfTargetMessage(portTarget, portSource, id, id),
                agentId);
        }
    }

    @Override
    public void addTargetFromMessage() {

        IKnowledgeSocial e = this.knowledge;

        Collection<PortOfTargetMessage> portOfTargetsMessageCollection = e.getPortOfTargetMessageCollection();

        for (PortOfTargetMessage message : portOfTargetsMessageCollection) {
            Set<ITarget> targetSet = new HashSet<ITarget>();
            ITarget target = new Target(message.getValue().toString(), message.getPortSource(),
                message.getPortTarget());
            targetSet.add(target);
            e.setTargetSet(targetSet);
        }
    }

    @Override
    public void updatePortFromMessage() {

        IKnowledgeSocial e = this.knowledge;

        Collection<ValuePortMessage> valuePortMessageCollection = e.getValuePortMessageCollection();

        System.out.println(valuePortMessageCollection);
        for (ValuePortMessage message : valuePortMessageCollection) {
            
            System.out.println(message.getPort());
            IPort p = e.getPortMap().get(message.getPort());
            p.setValue(message.getValue());
        }
    }

}
