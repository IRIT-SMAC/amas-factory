package fr.irit.smac.amasfactory.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.impl.BasicInfrastructure;
import fr.irit.smac.amasfactory.impl.ShutdownRuntimeException;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IInfraService<A extends IInfrastructureAgent<M>, M> {

    public void start();

    public void shutdown() throws ShutdownRuntimeException;

    public void setInfrastructure(BasicInfrastructure<A, M> basicInfrastructure);

}
