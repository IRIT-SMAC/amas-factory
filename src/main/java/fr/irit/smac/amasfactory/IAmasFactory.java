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

import fr.irit.smac.amasfactory.service.IServices;

/**
 * The interface IAmasFactory exposes methods to create an infrastructure.
 */
@FunctionalInterface
public interface IAmasFactory {

    /**
     * Creates a new IAmas object.
     *
     * @param <A>
     *            the generic type
     * @param <M>
     *            the generic type
     * @param configuration
     *            the configuration
     * @return the i infrastructure< a, m>
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public <T extends IServices<A>, A> IInfrastructure<T> createInfrastructure(InputStream configuration)
        throws IOException;
}
