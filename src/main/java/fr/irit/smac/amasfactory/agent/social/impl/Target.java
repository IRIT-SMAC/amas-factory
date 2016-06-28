package fr.irit.smac.amasfactory.agent.social.impl;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.social.ITarget;

public class Target implements ITarget, Serializable {

    private static final long serialVersionUID = -5644591395489274789L;
   
    @JsonProperty
	private String agentTarget;
	@JsonProperty
	private String portTarget;
	@JsonProperty
	private String portSource;
	
	public Target() {
		
	}
	
	public Target(String agentTarget, String portTarget, String portSource){
		this.agentTarget = agentTarget;
		this.portTarget = portTarget;
		this.portSource = portSource;
	}
	
	@Override
	public String getAgentId() {
		return this.agentTarget;
	}

    @Override
    public String toString() {
        return  agentTarget + "." + portTarget + "." + portSource;
    }

    @Override
    public String getPortTarget() {
        return this.portTarget;
    }

    @Override
    public String getPortSource() {
        return this.portSource;
    }

    
    
}
