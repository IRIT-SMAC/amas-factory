package fr.irit.smac.amasfactory.agent.impl;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IExtraKnowledge;
import fr.irit.smac.amasfactory.agent.IKnowledge;

/**
 * Simple implementation of an IKnowledge, implements Serializable for the data
 * sharing // persistence.
 * 
 * @author SVI
 *
 */
public class Knowledge implements IKnowledge, Serializable {

    private static final long serialVersionUID = -6416845878147222262L;

    @JsonProperty
    private String id;

    @JsonProperty
    protected Map<String,IExtraKnowledge> extraKnowledges;

    public Knowledge() {
        
    }

    /**
     * Instantiates a new simple knowledge.
     *
     * @param id
     *            the id
     */
    public Knowledge(String id) {
        this.id = id;
    }
    
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
    
    public Map<String, IExtraKnowledge> getExtraKnowledges() {
        return extraKnowledges;
    }
}
