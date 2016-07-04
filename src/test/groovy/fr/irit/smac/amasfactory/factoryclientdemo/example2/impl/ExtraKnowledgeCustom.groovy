package fr.irit.smac.amasfactory.factoryclientdemo.example2.impl

import fr.irit.smac.amasfactory.agent.impl.ExtraKnowledge
import fr.irit.smac.amasfactory.factoryclientdemo.example2.IExtraKnowledgeCustom

class ExtraKnowledgeCustom extends ExtraKnowledge implements IExtraKnowledgeCustom{

    private boolean send

    @Override
    public boolean getSend() {
        return this.send
    }

    @Override
    public void setSend(boolean send) {
        this.send = send
    }
}
