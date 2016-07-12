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

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Minimal interface of an input agent port
 * 
 * @author lemouzy
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IPort {

    /**
     * @return the id of the port
     */
    public String getId();

    /**
     * @return the class that represent the type of the values that can be set
     *         to that port
     */
    public Class<?> getType();

    /**
     * @return the value of the port
     */
    public Object getValue();

    /**
     * Sets the value of the port
     * 
     * @param value
     *            the new value of the port
     */
    public void setValue(Object value);
}
