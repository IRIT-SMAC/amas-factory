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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial;
import fr.irit.smac.amasfactory.agent.features.social.IPort;
import fr.irit.smac.amasfactory.agent.features.social.ITarget;
import fr.irit.smac.amasfactory.agent.impl.Knowledge;
import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.amasfactory.message.PortOfTargetMessage;
import fr.irit.smac.amasfactory.message.ValuePortMessage;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;

public class KnowledgeSocial extends Knowledge implements IKnowledgeSocial {

    @JsonProperty
    private Map<String,ITarget> targetMap;

    @JsonProperty
    protected Map<String, IPort> portMap;

    @JsonProperty
    private Class<?> outputType;

    @JsonProperty
    private Object outputValue;

    protected Collection<ValuePortMessage> sendToTargetMessageCollection;

    private Collection<PortOfTargetMessage> sendPortToTargetMessageCollection;

    protected IMsgBox<IMessage> msgBox;

    /**
     * Instantiates the knowledge of an agent.
     */
    public KnowledgeSocial() {

        targetMap = new HashMap<>();
        portMap = new HashMap<>();
        sendToTargetMessageCollection = new ArrayList<>();
        sendPortToTargetMessageCollection = new ArrayList<>();
    }

    @Override
    public void setTargetSet(Map<String,ITarget> targetMap) {
        this.targetMap = targetMap;
    }

    @Override
    public Map<String,ITarget> getTargetMap() {
        return targetMap;
    }

    @Override
    public Map<String, IPort> getPortMap() {
        return portMap;
    }

    @Override
    public Class<?> getOutputType() {
        return outputType;
    }

    @Override
    public Object getOutputValue() {
        return outputValue;
    }

    @Override
    public void setOutputValue(Object outputValue) {
        this.outputValue = outputValue;
    }

    @Override
    public Collection<ValuePortMessage> getValuePortMessageCollection() {
        return sendToTargetMessageCollection;
    }

    @Override
    public Collection<PortOfTargetMessage> getPortOfTargetMessageCollection() {
        return sendPortToTargetMessageCollection;
    }

    @Override
    public void setMsgBox(IMsgBox<IMessage> msgBox) {
        this.msgBox = msgBox;
    }

    @Override
    public IMsgBox<IMessage> getMsgBox() {
        return msgBox;
    }
}
