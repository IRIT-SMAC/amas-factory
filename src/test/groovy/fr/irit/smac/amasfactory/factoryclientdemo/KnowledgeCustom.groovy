package fr.irit.smac.amasfactory.factoryclientdemo

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.social.impl.KnowledgeSocial

class KnowledgeCustom extends KnowledgeSocial implements IKnowledgeCustom{

    private int count

    public KnowledgeCustom() {
        super()
        this.count = 0
    }
    
    public void increment() {
        count++
    }
    
    public int getCount() {
        this.count
    }
}
