package fr.irit.smac.amasfactory.agent;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;

/**
 * Interface that defines the interaction capabilities of an agent
 * with the infrastructure
 * @author lemouzy
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IInfrastructureAgent<M>
{
    public void init(IAgentSideInfrastructure<M> infrastructure, String agentId);
    
    public String getId();
    
    public IKnowledge getInnerKnowledge();
}
