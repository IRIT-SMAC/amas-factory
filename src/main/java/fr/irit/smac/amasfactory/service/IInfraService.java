package fr.irit.smac.amasfactory.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.impl.BasicInfrastructure;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IInfraService<A extends IInfrastructureAgent<M>, M> {
    public void init(IInfrastructure<A, M> infrastructure);

    public void start();

    public void shutdown();

    public void setInfrastructure(BasicInfrastructure<A, M> basicInfrastructure);

}
