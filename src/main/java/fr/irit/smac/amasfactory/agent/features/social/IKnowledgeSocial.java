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

import java.util.Collection;
import java.util.Map;

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
