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
package fr.irit.smac.amasfactory.agent.features.social.impl;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.features.social.IPort;

/**
 * The implementation of a port
 */
public class Port implements IPort {

    @JsonProperty
    protected String id;

    protected Set<Object> value = new HashSet<>();

    public Port() {
        // Needed by Jackson
    }

    public Port(String id) {

        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Set<Object> getValue() {
        return value;
    }

    @Override
    public void addValue(Object value) {
        this.value.add(value);
    }

    @Override
    public void setValue(Set<Object> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" + id + ": " + id + "]";
    }
}
