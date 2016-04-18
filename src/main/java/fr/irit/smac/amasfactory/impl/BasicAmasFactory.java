package fr.irit.smac.amasfactory.impl;

import java.io.IOException;
import java.io.InputStream;

import fr.irit.smac.amasfactory.IAmasFactory;
import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.util.parser.impl.AmasFactoryParser;

/**
 * 
 * 
 * 
 * @author lemouzy
 *
 */
public class BasicAmasFactory implements IAmasFactory {

    @Override
    public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M> createInfrastructure(
        InputStream configuration) throws IOException {

        AmasFactoryParser parser = AmasFactoryParser.getInstance();

        return parser.parseInfrastructure(configuration);
    }
}
