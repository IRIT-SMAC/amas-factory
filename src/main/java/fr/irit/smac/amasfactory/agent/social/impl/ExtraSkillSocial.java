package fr.irit.smac.amasfactory.agent.social.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import fr.irit.smac.amasfactory.agent.EExtraKnowledgeSkill;
import fr.irit.smac.amasfactory.agent.impl.ExtraSkill;
import fr.irit.smac.amasfactory.agent.social.IExtraKnowledgeSocial;
import fr.irit.smac.amasfactory.agent.social.IExtraSkillSocial;
import fr.irit.smac.amasfactory.agent.social.IPort;
import fr.irit.smac.amasfactory.agent.social.ITarget;
import fr.irit.smac.amasfactory.message.PortOfTargetMessage;
import fr.irit.smac.amasfactory.message.ValuePortMessage;

public class ExtraSkillSocial extends ExtraSkill implements IExtraSkillSocial {


    private static final long serialVersionUID = -1923766227894976095L;

    public ExtraSkillSocial() {

    }

    @Override
    public void sendOutputValue() {

        IExtraKnowledgeSocial e = (IExtraKnowledgeSocial) this.knowledge.getExtraKnowledge().get(EExtraKnowledgeSkill.SOCIAL.getName());
        Object value = e.getOutputValue();

        for (ITarget target : e.getTargetSet()) {
            String agentId = target.getAgentId();
            String port = target.getPortTarget();
            // logger.info("send to target " + target.getAgentId() + "
            // message= " + value);
            e.getMsgBox().send(
                new ValuePortMessage(port, value),
                agentId);
        }
    }

    @Override
    public void sendPort() {

        IExtraKnowledgeSocial e = (IExtraKnowledgeSocial) this.knowledge.getExtraKnowledge().get(EExtraKnowledgeSkill.SOCIAL.getName());

        Set<ITarget> targets = e.getTargetSet();

        for (ITarget target : targets) {
            String agentId = target.getAgentId();
            String portTarget = target.getPortTarget();
            String portSource = target.getPortSource();
            e.getMsgBox().send(new PortOfTargetMessage(portTarget, portSource, this.knowledge.getId()),
                agentId);
        }
    }

    @Override
    public void addTargetFromMessage() {

        IExtraKnowledgeSocial e = (IExtraKnowledgeSocial) this.knowledge.getExtraKnowledge().get(EExtraKnowledgeSkill.SOCIAL.getName());

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

        IExtraKnowledgeSocial e = (IExtraKnowledgeSocial) this.knowledge.getExtraKnowledge().get(EExtraKnowledgeSkill.SOCIAL.getName());

        Collection<ValuePortMessage> valuePortMessageCollection = e.getValuePortMessageCollection();

        for (ValuePortMessage message : valuePortMessageCollection) {
            IPort p = e.getPortMap().get(message.getPort());
            p.setValue(message.getValue());
        }
    }



}
