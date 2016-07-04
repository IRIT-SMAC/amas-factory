package fr.irit.smac.amasfactory.agent.features.basic.impl;

import fr.irit.smac.amasfactory.agent.features.basic.IKnowledgeBasic;
import fr.irit.smac.amasfactory.agent.impl.Knowledge;

public class KnowledgeBasic extends Knowledge implements IKnowledgeBasic{

    private static final long serialVersionUID = -6074066404770071197L;
    private String id;

    @Override
    public String getId() {
        return this.id;
    }
    
}
