package fr.irit.smac.amasfactory.impl;

import java.io.IOException;
import java.io.InputStream;

import fr.irit.smac.amasfactory.IAmasFactory;
import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.util.deserializer.impl.AmasFactoryDeserializer;

/**
 * A factory for creating a BasicAmas.
 *
 * @author lemouzy
 * @param <M>
 * @param <A>
 */
public class BasicAmasFactory<A extends IAgent<M>, M> implements IAmasFactory {

    protected Class<IInfrastructure<A, M>> infrastructureClass;

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.IAmasFactory#createInfrastructure(java.io.
     * InputStream)
     */
    @Override
    public <A extends IAgent<M>, M> IInfrastructure<A, M> createInfrastructure(
        InputStream configuration) throws IOException {

        AmasFactoryDeserializer parser = AmasFactoryDeserializer.getInstance();

        return (IInfrastructure<A, M>) parser.deserializeInfrastructure(configuration);
    }
}
