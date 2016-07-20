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
package fr.irit.smac.amasfactory.agent.features.social;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A port interprets received data in the same way.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IPort {

    /**
     * @return the id of the port
     */
    public String getId();

    /**
     * @return the received values
     */
    public Set<Object> getValue();

    /**
     * Sends a value to the port
     * 
     * @param value
     */
    void addValue(Object value);

    /**
     * Sets the values of the port
     * 
     * @param values
     *            the values of the port
     */
    void setValue(Set<Object> value);
}
