/*
 * #%L
 * amas-factory
 * %%
 * Copyright (C) 2015 - 2016 IRIT - SMAC Team and Brennus Analytics
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package fr.irit.smac.amasfactory.agent.features.social.impl;

import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial;
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial;
import fr.irit.smac.amasfactory.agent.features.social.ITarget;
import fr.irit.smac.amasfactory.agent.impl.Skill;
import fr.irit.smac.amasfactory.message.EMessageType;
import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.amasfactory.message.PortOfTargetMessage;
import fr.irit.smac.amasfactory.message.ValuePortMessage;

/**
 * The implementation of the social skill
 *
 * @param <K>
 *            the knowledge used by the feature
 */
public class SkillSocial<K extends IKnowledgeSocial> extends Skill<K>implements ISkillSocial<K> {

    public SkillSocial() {
        // Needed by Jackson
    }

    @Override
    public void addTargetFromMessage(PortOfTargetMessage message) {

        ITarget target = new Target(message.getSender(), message.getPortSource(),
            message.getPortTarget());
        knowledge.getTargetMap().put(message.getSender().concat(message.getPortSource()), target);
    }

    @Override
    public void processMsg(IMessage message) {

        if (message != null) {
            if (message.getMessageType().getName().equals(EMessageType.SEND_TO_TARGET_MESSAGE.getName())) {
                ValuePortMessage m = (ValuePortMessage) message;
                knowledge.getPortMap().get(m.getPort()).addValue(m.getValue());
            }
            else if (message.getMessageType().getName()
                .equals(EMessageType.SEND_PORT_TO_TARGET_MESSAGE.getName())) {
                PortOfTargetMessage m = (PortOfTargetMessage) message;
                knowledge.getPortMap().get(m.getPortTarget()).addValue(m.getValue());
                addTargetFromMessage(m);
            }
        }
    }

    @Override
    public void clearPortMap() {

        knowledge.getPortMap().forEach((k, v) -> v.getValue().clear());
    }

    @Override
    public void sendPortToTarget(String nameTarget, String id) {

        ITarget target = knowledge.getTargetMap().get(nameTarget);
        String agentId = target.getAgentId();
        String portTarget = target.getPortTarget();
        String portSource = target.getPortSource();

        knowledge.getMsgBox().send(new PortOfTargetMessage(portTarget, portSource, null, id),
            agentId);
    }

    @Override
    public void sendDataToPortTarget(String nameTarget, Object data, String senderId) {

        ITarget target = knowledge.getTargetMap().get(nameTarget);
        String agentId = target.getAgentId();
        String portTarget = target.getPortTarget();
        knowledge.getMsgBox().send(new ValuePortMessage(portTarget, data, senderId), agentId);
    }

}
