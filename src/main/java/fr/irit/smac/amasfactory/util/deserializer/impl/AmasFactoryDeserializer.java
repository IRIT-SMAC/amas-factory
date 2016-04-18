package fr.irit.smac.amasfactory.util.deserializer.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.impl.BasicInfrastructure;
import fr.irit.smac.amasfactory.util.deserializer.IAmasFactoryDeserializer;
import fr.irit.smac.amasfactory.util.deserializer.impl.AmasFactoryDeserializer;
import fr.irit.smac.libs.tooling.scheduling.impl.system.SynchronizedSystemStrategy;

/**
 * AmasFactoryDeserializer is a singleton allowing to create a multi-agent
 * system from a configuration file in Json.
 */
public class AmasFactoryDeserializer implements IAmasFactoryDeserializer {

    private static final Logger LOGGER = Logger.getLogger(SynchronizedSystemStrategy.class.getName());

    private static final AmasFactoryDeserializer INSTANCE = new AmasFactoryDeserializer();

    /**
     * Instantiates a new amas factory deserializer.
     */
    protected AmasFactoryDeserializer() {

    }

    /**
     * Gets the single instance of AmasFactoryDeserializer.
     *
     * @return single instance of AmasFactoryDeserializer
     */
    public static AmasFactoryDeserializer getInstance() {
        return INSTANCE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.util.parser.IAmasFactoryParser#
     * parseInfrastructure(java.io.InputStream)
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M> deserializeInfrastructure(
        InputStream configuration)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        IInfrastructure<A, M> infrastructure = null;
        try {

            JsonElement configurationJson = new JsonParser().parse(new InputStreamReader(configuration));
            infrastructure = mapper.readValue(configurationJson.getAsJsonObject().toString(),
                BasicInfrastructure.class);

        }
        catch (IOException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            throw e;
        }

        return infrastructure;
    }
}
