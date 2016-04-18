package fr.irit.smac.amasfactory.util.parser.impl;

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
import fr.irit.smac.amasfactory.util.parser.IAmasFactoryParser;
import fr.irit.smac.libs.tooling.scheduling.impl.system.SynchronizedSystemStrategy;

public class AmasFactoryParser implements IAmasFactoryParser {

    private static final Logger      LOGGER   = Logger.getLogger(SynchronizedSystemStrategy.class.getName());
    private static final AmasFactoryParser INSTANCE = new AmasFactoryParser();

    protected AmasFactoryParser() {

    }

    public static AmasFactoryParser getInstance() {
        return INSTANCE;
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M> parseInfrastructure(InputStream configuration)
        throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        IInfrastructure<A, M> infrastructure = null;
        try {

            JsonElement configurationJson = new JsonParser().parse(new InputStreamReader(configuration));
            infrastructure = mapper.readValue(configurationJson.getAsJsonObject().toString(),
                BasicInfrastructure.class);
            infrastructure.init(null);

        }
        catch (IOException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            throw e;
        }

        return infrastructure;
    }
}
