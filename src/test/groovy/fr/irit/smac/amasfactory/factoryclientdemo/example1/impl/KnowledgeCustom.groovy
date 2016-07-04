package fr.irit.smac.amasfactory.factoryclientdemo.example1.impl

import fr.irit.smac.amasfactory.agent.impl.Knowledge
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IKnowledgeCustom

class KnowledgeCustom extends Knowledge implements IKnowledgeCustom, Serializable{

    private int count

    public KnowledgeCustom() {
        this.count = 0
    }
    
    public int getCount() {
        this.count
    }
    
    public void setCount(int count) {
        this.count = count
    }
}
