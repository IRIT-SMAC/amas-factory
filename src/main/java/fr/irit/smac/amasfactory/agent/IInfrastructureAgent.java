package fr.irit.smac.amasfactory.agent;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;

/**
 * Interface that defines the interaction capabilities of an agent
 * with the infrastructure
 * @author lemouzy
 *
 */
public interface IInfrastructureAgent<M>
{
    public void init(IAgentSideInfrastructure<M> infrastructure, String agentId);
    
    public String getId();
}
