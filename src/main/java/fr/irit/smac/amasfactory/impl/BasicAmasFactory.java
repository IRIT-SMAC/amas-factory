package fr.irit.smac.amasfactory.impl;

import java.io.InputStream;

import fr.irit.smac.amasfactory.IAmasFactory;
import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.util.impl.AmasFactoryParser;

/**
 * 
 * 
 * 
 * @author lemouzy
 *
 */
public class BasicAmasFactory implements IAmasFactory {

    private AmasFactoryParser amasFactoryParser;
    
    @Override
    public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M> createInfrastructure(
        InputStream configuration) {

        this.amasFactoryParser = AmasFactoryParser.getInstance();
        this.amasFactoryParser.init(configuration);
        
        IInfrastructure<A, M> infrastructure = amasFactoryParser.instantiateAndInitInfrastructure();
        
        amasFactoryParser.instantiateAndInitAgents(infrastructure);

        return infrastructure;
    }
}
