package fr.irit.smac.amasfactory.factoryclientdemo.example3.impl

import fr.irit.smac.amasfactory.agent.impl.Knowledge
import fr.irit.smac.amasfactory.factoryclientdemo.example3.IKnowledgeCustom

public class KnowledgeCustom extends Knowledge implements IKnowledgeCustom {

    private boolean send
    private String value

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

    @Override
    public String getValue() {
        return this.value
    }

    @Override
    public void setValue(String value) {
        this.value = value
    }
}
