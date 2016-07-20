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

import java.util.Map;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;

/**
 * The social knowledge. The feature linked to this knowledge allows to the
 * agent to communicate via ports. An agent can send values directly to the port
 * of an agent. A port interprets a data in the same way. It makes easier the
 * communication between the agents. This feature uses the messaging service.
 */
public interface IKnowledgeSocial extends IKnowledge {

    /**
     * @return a map containing the ports of an agent
     */
    public Map<String, IPort> getPortMap();

    /**
     * @return a map containing the targets of an agent
     */
    public Map<String, ITarget> getTargetMap();

    public IMsgBox<IMessage> getMsgBox();

    public void setMsgBox(IMsgBox<IMessage> msgBox);

    public void setTargetMap(Map<String, ITarget> targetMap);

    public void setPortMap(Map<String, IPort> portMap);
}
