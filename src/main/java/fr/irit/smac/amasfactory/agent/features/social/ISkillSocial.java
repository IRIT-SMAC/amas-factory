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
package fr.irit.smac.amasfactory.agent.features.social;

import fr.irit.smac.amasfactory.agent.ISkill;
import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.amasfactory.message.PortOfTargetMessage;

/**
 * The social skill. The feature linked to this knowledge allows to the agent to
 * communicate via ports. An agent can send values directly to the port of an
 * agent. A port interprets a data in the same way. It makes easier the
 * communication between the agents. This feature uses the messaging service.
 *
 * @param <K>
 *            the knowledge used by the skill
 */
public interface ISkillSocial<K extends IKnowledgeSocial> extends ISkill<K> {

    /**
     * Add the targets according if the name of the port of an agent is received
     * 
     * @param message
     */
    public void addTargetFromMessage(PortOfTargetMessage message);

    /**
     * Handles the received messages
     * 
     * @param message
     */
    public void processMsg(IMessage message);

    /**
     * Clears the values received of the port map
     */
    public void clearPortMap();

    /**
     * Sends the port of the sender to the receiver. It allows to the receiver
     * to send messages to this port
     * 
     * @param nameTarget
     * @param id
     */
    public void sendPortToTarget(String nameTarget, String id);

    /**
     * Sends data to the port of a target
     * 
     * @param idTarget
     *            the id of target
     * @param data
     *            the data to send
     * @param senderId
     *            the id of the sender
     */
    public void sendDataToPortTarget(String idTarget, Object data, String senderId);
}
