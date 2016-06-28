/*
 * 
 */
package fr.irit.smac.amasfactory.agent.impl;

import org.slf4j.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;
import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;

/**
 * Abstract class used by subclasses implementing an agent
 *
 * @param <M>
 *            the generic type
 */
public abstract class Agent<M> implements IAgent<M> {

    @JsonProperty
    private String id;

    private IAgentSideInfrastructure<M> infrastructure;

    @JsonProperty
    protected IKnowledge knowledge;

    protected IMsgBox<M> msgBox;
    protected Logger     logger;

    /**
     * Gets the infra.
     *
     * @return the infra
     */
    protected IAgentSideInfrastructure<M> getInfra() {
        return this.infrastructure;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.agent.IInfrastructureAgent#getId()
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Inits the parameters.
     */
    protected void initParameters() {

        this.msgBox = this.getInfra().getMessagingService().getMsgBox(this.getId());
        this.logger = this.getInfra().getLoggingService().getAgentLogger(this.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.amasfactory.agent.IInfrastructureAgent#getInnerKnowledge()
     */
    @Override
    public IKnowledge getKnowledge() {
        return this.knowledge;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.amasfactory.agent.IInfrastructureAgent#init(fr.irit.smac.
     * amasfactory.IAgentSideInfrastructure, java.lang.String)
     */
    @Override
    public void init(IAgentSideInfrastructure<M> infrastructure, String id) {
        this.infrastructure = infrastructure;
        this.id = id;

        this.initParameters();
    }
}
