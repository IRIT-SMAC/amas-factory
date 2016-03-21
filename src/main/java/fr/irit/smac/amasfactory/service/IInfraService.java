package fr.irit.smac.amasfactory.service;

import com.google.gson.JsonElement;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;

public interface IInfraService<A extends IInfrastructureAgent<M>,M>
{
    public void init(IInfrastructure<A, M> infrastructure, JsonElement configuration);

    public void start();

    public void shutdown();
}
