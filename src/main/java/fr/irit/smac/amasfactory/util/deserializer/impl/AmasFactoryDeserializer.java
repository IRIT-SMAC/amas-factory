/*
 * #%L
 * amas-factory
 * %%
 * Copyright (C) 2015 - 2016 IRIT - SMAC Team and Brennus Analytics
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
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
import fr.irit.smac.amasfactory.impl.BasicInfrastructure;
import fr.irit.smac.amasfactory.service.IServices;
import fr.irit.smac.amasfactory.util.deserializer.IAmasFactoryDeserializer;
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
    @SuppressWarnings("unchecked")
    @Override
    public <T extends IServices<A>,A> IInfrastructure<T,A> deserializeInfrastructure(
        InputStream configuration)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        IInfrastructure<T,A> infrastructure = null;
        try {

            JsonElement configurationJson = new JsonParser().parse(new InputStreamReader(configuration));
            infrastructure = mapper.readValue(configurationJson.getAsJsonObject().toString(),
                BasicInfrastructure.class);
            infrastructure.start();

        }
        catch (IOException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            throw e;
        }

        return infrastructure;
    }
}
