package fr.irit.smac.amasfactory.agent.features.social.impl;

import java.util.Collection;
import java.util.Map;

import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial;
import fr.irit.smac.amasfactory.agent.features.social.IPort;
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial;
import fr.irit.smac.amasfactory.agent.features.social.ITarget;
import fr.irit.smac.amasfactory.agent.impl.Skill;
import fr.irit.smac.amasfactory.message.PortOfTargetMessage;
import fr.irit.smac.amasfactory.message.ValuePortMessage;

public class SkillSocial<K extends IKnowledgeSocial> extends Skill<K>implements ISkillSocial<K> {

    public SkillSocial() {

    }

    @Override
    public void sendOutputValue(String id) {

        Object value = this.knowledge.getOutputValue();

        this.knowledge.getTargetMap().forEach((k, v) -> {
            String agentId = v.getAgentId();
            String port = v.getPortTarget();
            // logger.info("send to target " + target.getAgentId() + "
            // message= " + value);
            this.knowledge.getMsgBox().send(
                new ValuePortMessage(port, value, id),
                agentId);
        });

    }

    @Override
    public void sendPort(String id) {

        this.knowledge.getTargetMap().forEach((k, v) -> {

            String agentId = v.getAgentId();
            String portTarget = v.getPortTarget();
            String portSource = v.getPortSource();
            this.knowledge.getMsgBox().send(new PortOfTargetMessage(portTarget, portSource, id, id),
                agentId);
        });
    }

    @Override
    public void addTargetFromMessage() {

        Collection<PortOfTargetMessage> portOfTargetsMessageCollection = this.knowledge
            .getPortOfTargetMessageCollection();
        Map<String, ITarget> targetMap = this.knowledge.getTargetMap();

        for (PortOfTargetMessage message : portOfTargetsMessageCollection) {
            ITarget target = new Target(message.getValue().toString(), message.getPortSource(),
                message.getPortTarget());
            targetMap.put(message.getValue().toString().concat(message.getPortSource()), target);
        }
    }

    @Override
    public void updatePortFromMessage() {

        IKnowledgeSocial e = this.knowledge;

        Collection<ValuePortMessage> valuePortMessageCollection = e.getValuePortMessageCollection();

        for (ValuePortMessage message : valuePortMessageCollection) {

            IPort p = e.getPortMap().get(message.getPort());
            p.setValue(message.getValue());
        }
    }

    @Override
    public void sendValueToTargets(String id) {

        this.knowledge.getTargetMap().forEach((k, v) -> {
            this.knowledge.getMsgBox().send(new ValuePortMessage(v.getPortTarget(), v.getValue(), id), v.getAgentId());
        });
    }

}
