package fr.irit.smac.amasfactory.factoryclientdemo.example2

import fr.irit.smac.amasfactory.agent.ISkill
import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.impl.KnowledgeSocial

interface ISkillCustom<K extends IKnowledgeCustom> extends ISkill<K>{

    public void processMessages(IKnowledgeSocial knowledgeSocial)
    
    public boolean checkPortMapFull(IKnowledgeSocial knowledgeSocial)
    
    public void getOutputValue(IKnowledgeSocial knowledgeSocial)
}
