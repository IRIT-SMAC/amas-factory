package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl

import fr.irit.smac.amasfactory.agent.impl.ExtraKnowledge;
import fr.irit.smac.amasfactory.agent.impl.Knowledge
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IExtraKnowledgeCustom

class ExtraKnowledgeCustom extends ExtraKnowledge implements IExtraKnowledgeCustom, Serializable{

    private int count

    public ExtraKnowledgeCustom() {
        this.count = 0
    }
    
    public int getCount() {
        this.count
    }
    
    public void setCount(int count) {
        this.count = count
    }
}
