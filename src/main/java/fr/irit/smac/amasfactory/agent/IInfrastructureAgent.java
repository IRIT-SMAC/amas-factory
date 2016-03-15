package fr.irit.smac.amasfactory.agent;

import com.google.gson.JsonObject;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;

/**
 * Interface that defines the interaction capabilities of an agent
 * with the infrastructure
 * @author lemouzy
 *
 */
public interface IInfrastructureAgent<M>
{
    public void init(IAgentSideInfrastructure<M> infrastructure, String agentId, IKnowledge knowledge, JsonObject configuration);
    
    public String getId();
    
    public IKnowledge getInnerKnowledge();
}
