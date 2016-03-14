package fr.irit.smac.amasfactory.util;

import com.google.gson.JsonObject;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;

public interface IAmasFactoryInstantiator {

    /**
     * Instantiate an object from json configuration that follow the example below
     * 
     * {
     *    className:"com.brennus.amas.influencenetwork.datasource.impl.CsvBasicDataSource",
     *    configuration:{ some:"json",parameters:""}
     * }
     * 
     * @param configuration
     * @return
     */
    public <T> T instantiateAndInitObjectFromConfig(JsonObject configuration) ;
    
    
    /**
     * Instantiate an IInfrastructureAgent from json configuration that follow the example below
     * 
     * {
     *    className:"com.brennus.amas.influencenetwork.datasource.impl.CsvBasicDataSource",
     *    configuration:{ some:"json",parameters:""}
     * }
     * 
     * @param configuration
     * @return
     */
    public <M> IInfrastructureAgent<M> instantiateAndInitAgentFromConfig(IAgentSideInfrastructure<M> infrastructure, String agentId, JsonObject configuration) ;
    
    /**
     * Instantiate an object from json configuration that follow the example below
     * 
     * {
     *    className:"com.brennus.amas.influencenetwork.datasource.impl.CsvBasicDataSource",
     *    configuration:{ some:"json",parameters:""}
     * }
     * 
     * @param configuration
     * @return
     */
    public <A extends IInfrastructureAgent<M>,M> IInfraService<A,M> instantiateAndInitService(IAgentSideInfrastructure<M> infrastructure, JsonObject configuration) ;
    
}
