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
package fr.irit.smac.amasfactory.impl;

import java.io.IOException;
import java.io.InputStream;

import fr.irit.smac.amasfactory.IAmasFactory;
import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.service.IServices;
import fr.irit.smac.amasfactory.util.deserializer.impl.AmasFactoryDeserializer;

/**
 * A factory to create an Amas
 *
 * @param <T>
 *            the services used
 * @param <A>
 *            the type of agent
 */
public class AmasFactory<T extends IServices<A>,A> implements IAmasFactory<T> {

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.IAmasFactory#createInfrastructure(java.io.
     * InputStream)
     */
    @Override
    public IInfrastructure<T> createInfrastructure(
        InputStream configuration) throws IOException {

        AmasFactoryDeserializer parser = AmasFactoryDeserializer.getInstance();
        IInfrastructure<T> infrastructure = parser.deserializeInfrastructure(configuration);
        infrastructure.start();

        return infrastructure;
    }

    @Override
    public IInfrastructure<T> createInfrastructure() {
        return new Infrastructure<>();
    }
}
