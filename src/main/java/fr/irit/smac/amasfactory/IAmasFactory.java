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
package fr.irit.smac.amasfactory;

import java.io.IOException;
import java.io.InputStream;

import fr.irit.smac.amasfactory.infrastructure.IInfrastructure;

/**
 * The interface IAmasFactory exposes methods to create an infrastructure.
 *
 * @param <T>
 *            the services
 */
public interface IAmasFactory<T> {

    /**
     * Creates a new infrastructure with a json file
     * 
     * @param configuration
     *            the json file
     * @return the infrastructure
     * @throws IOException
     */
    public IInfrastructure<T> createInfrastructure(InputStream configuration)
        throws IOException;

    /**
     * Creates a new infrastructure without a json file
     * 
     * @return
     */
    public IInfrastructure<T> createInfrastructure();
}
