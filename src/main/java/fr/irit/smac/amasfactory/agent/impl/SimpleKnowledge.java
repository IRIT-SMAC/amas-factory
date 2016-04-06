package fr.irit.smac.amasfactory.agent.impl;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IKnowledge;

/**
 * Simple implementation of an IKnowledge, implements Serializable for the data sharing // persistence.
 * @author SVI
 *
 */
public class SimpleKnowledge implements IKnowledge, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6416845878147222262L;
	
	@JsonProperty
	private String id;
	
	public SimpleKnowledge() {
		
	}
	
	public SimpleKnowledge(String id) {
		this.id = id;
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	/**
	 * Non interface method, used for specific debug purpose.
	 * @param id
	 */
	public void setId(String id){
		this.id = id;
	}

}
