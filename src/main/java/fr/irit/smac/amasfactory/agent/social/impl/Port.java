package fr.irit.smac.amasfactory.agent.social.impl;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.social.IPort;

public class Port implements IPort, Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    protected String   id;
    
    @JsonProperty
    protected Class<?> type;
    
    @JsonProperty
    protected Object value;

    public Port() {
    	
    }
    
    public Port(String id, Class<?> type) {

        this.id = id;
        this.type = type;
    }

    @Override
    public String getId() {

        return this.id;
    }

    @Override
    public Class<?> getType() {

        return this.type;
    }
    
    @Override 
    public Object getValue() {
        
        return this.value;
    }
    
    @Override
    public void setValue(Object value) {
        
        this.value = value;
    }

    @Override
    public String toString() {
        return  "["+id + ": " + type + " " + value + "]";
    }
}
