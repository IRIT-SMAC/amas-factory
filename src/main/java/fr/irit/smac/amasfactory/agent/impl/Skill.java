package fr.irit.smac.amasfactory.agent.impl;

import org.slf4j.Logger;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;

public abstract class Skill<K extends IKnowledge> implements ISkill<K> {

    protected K knowledge;
    
    protected Logger logger;

    @Override
    public void setKnowledge(K knowledge) {
        this.knowledge = knowledge;
    }
    
    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
}
