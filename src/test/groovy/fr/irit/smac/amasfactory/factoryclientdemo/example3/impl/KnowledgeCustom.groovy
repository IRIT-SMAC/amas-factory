package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl;

import fr.irit.smac.amasfactory.agent.impl.Knowledge
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IKnowledgeCustom

public class KnowledgeCustom extends Knowledge implements IKnowledgeCustom {

    private boolean send

    public KnowledgeCustom() {
        this.send = false
    }
    
    @Override
    public boolean getSend() {
        return this.send
    }

    @Override
    public void setSend(boolean send) {
        this.send = send
    }
}