package fr.irit.smac.amasfactory.agent;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;

/**
 * Interface that defines the interaction capabilities of an agent with the
 * infrastructure.
 *
 * @author lemouzy
 * @param <M>
 *            the generic type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IInfrastructureAgent<M> {

    /**
     * Inits the agent.
     *
     * @param infrastructure
     *            the infrastructure
     * @param agentId
     *            the agent id
     */
    public void init(IAgentSideInfrastructure<M> infrastructure, String agentId);

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId();

    /**
     * Gets the inner knowledge.
     *
     * @return the inner knowledge
     */
    public IKnowledge getInnerKnowledge();
}
