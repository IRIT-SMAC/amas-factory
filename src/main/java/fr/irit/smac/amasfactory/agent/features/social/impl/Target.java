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

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.features.social.ITarget;

/**
 * The implementation of the target
 */
public class Target implements ITarget {

    @JsonProperty
    private String agentTarget;
    @JsonProperty
    private String portTarget;
    @JsonProperty
    private String portSource;

    private Object value;

    public Target() {
        // Needed by Jackson
    }

    public Target(String agentTarget, String portTarget, String portSource) {
        this.agentTarget = agentTarget;
        this.portTarget = portTarget;
        this.portSource = portSource;
    }

    @Override
    public String getAgentId() {
        return agentTarget;
    }

    @Override
    public String toString() {
        return agentTarget + "." + portTarget + "." + portSource;
    }

    @Override
    public String getPortTarget() {
        return portTarget;
    }

    @Override
    public String getPortSource() {
        return portSource;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }
}
