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

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.service.IServices;

/**
 * A BasicInfrastructure controls the services of the system
 *
 * @param <A>
 *            the generic type
 * @param the
 *            generic type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public class Infrastructure<T extends IServices<A>, A>
    implements IInfrastructure<T> {

    protected T services;

    public Infrastructure() {
        // Needed by Jackson
    }

    @Override
    public T getServices() {

        return this.services;
    }

    @Override
    public void start() {

        this.services.start();

    }

    @Override
    public void shutdown() throws ShutdownRuntimeException {

        this.services.stop();
    }

}
