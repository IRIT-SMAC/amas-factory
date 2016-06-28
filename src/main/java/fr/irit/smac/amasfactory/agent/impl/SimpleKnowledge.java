/*
 * 
 */
package fr.irit.smac.amasfactory.agent.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.IPort;
import fr.irit.smac.amasfactory.agent.ITarget;
import fr.irit.smac.amasfactory.message.PortOfTargetMessage;
import fr.irit.smac.amasfactory.message.ValuePortMessage;

/**
 * Simple implementation of an IKnowledge, implements Serializable for the data
 * sharing // persistence.
 * 
 * @author SVI
 *
 */
public class SimpleKnowledge implements IKnowledge, Serializable {

    private static final long serialVersionUID = -6416845878147222262L;

    @JsonProperty
    private String id;

    @JsonProperty
    private Set<ITarget> targetSet;

    @JsonProperty
    protected Map<String, IPort> portMap;

    @JsonProperty
    private Class<?> outputType;

    @JsonProperty
    private Object outputValue;

    protected Collection<ValuePortMessage> sendToTargetMessageCollection;

    private Collection<PortOfTargetMessage> sendPortToTargetMessageCollection;

    /**
     * Instantiates a new simple knowledge.
     */
    public SimpleKnowledge() {

        this.targetSet = new HashSet<>();
        this.sendToTargetMessageCollection = new ArrayList<>();
        this.sendPortToTargetMessageCollection = new ArrayList<>();

    }

    /**
     * Instantiates a new simple knowledge.
     *
     * @param id
     *            the id
     */
    public SimpleKnowledge(String id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.agent.IKnowledge#getId()
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Non interface method, used for specific debug purpose.
     *
     * @param id
     *            the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setTargetSet(Set<ITarget> targetSet) {
        this.targetSet = targetSet;
    }

    @Override
    public Set<ITarget> getTargetSet() {
        return targetSet;
    }

    @Override
    public Map<String, IPort> getPortMap() {
        return this.portMap;
    }

    @Override
    public Class<?> getOutputType() {
        return outputType;
    }

    @Override
    public Object getOutputValue() {
        return this.outputValue;
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
}
