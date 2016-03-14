package fr.irit.smac.amasfactory;

import java.io.InputStream;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;

public interface IAmasFactory
{
    public final static String INFRA_CLASS_NAME_CONFIG_KEY = "infrastructureClassName";
    
    public <A extends IInfrastructureAgent<M>,M> IInfrastructure<A,M> createInfrastructure(InputStream configuration);
}
