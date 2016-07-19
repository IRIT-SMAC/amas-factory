package fr.irit.smac.amasfactory.factoryclientdemo.example2

import fr.irit.smac.amasfactory.agent.IKnowledge

interface IKnowledgeCustom extends IKnowledge{

    public boolean getSend()
    
    public void setSend(boolean send)
    
    public String getValue()
    
    public void setValue(String value)
}
