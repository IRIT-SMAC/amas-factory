package fr.irit.smac.amasfactory.agent.impl;

import java.io.Serializable;

import fr.irit.smac.amasfactory.agent.IKnowledge;

public class SimpleKnowledge implements IKnowledge, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6416845878147222262L;
	String id;
	
	public SimpleKnowledge(String id) {
		this.id = id;
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}

}
