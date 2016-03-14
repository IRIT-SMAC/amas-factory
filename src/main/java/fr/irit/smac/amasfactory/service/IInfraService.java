package fr.irit.smac.amasfactory.service;

import java.util.Map;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;

public interface IInfraService<A extends IInfrastructureAgent<M>,M>
{
    public void init(IInfrastructure<A,M> infrastructure, Map<String,Object> parameters); // TODO Change Map to JSon Element in order to have more flexibility

    public void start();

    public void shutdown();
}
