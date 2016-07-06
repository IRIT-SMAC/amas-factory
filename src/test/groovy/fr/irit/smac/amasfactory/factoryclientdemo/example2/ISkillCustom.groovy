package fr.irit.smac.amasfactory.factoryclientdemo.example2

import fr.irit.smac.amasfactory.agent.features.social.impl.KnowledgeSocial
import fr.irit.smac.amasfactory.agent.features.social.impl.SkillSocial
import fr.irit.smac.amasfactory.message.IMessage
import fr.irit.smac.libs.tooling.messaging.IMsgBox

interface ISkillCustom {

    public void processMessages(KnowledgeSocial knowledgeSocial)
    
    public boolean checkPortMapFull(KnowledgeSocial knowledgeSocial)
    
    public void getOutputValue(KnowledgeSocial knowledgeSocial)
}
