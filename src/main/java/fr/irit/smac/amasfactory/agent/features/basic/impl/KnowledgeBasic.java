package fr.irit.smac.amasfactory.agent.features.basic.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.features.basic.IKnowledgeBasic;
import fr.irit.smac.amasfactory.agent.impl.Knowledge;

public class KnowledgeBasic extends Knowledge implements IKnowledgeBasic{

    private static final long serialVersionUID = -6074066404770071197L;
    
    @JsonProperty
    private String id;

    public KnowledgeBasic() {
        
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
}
