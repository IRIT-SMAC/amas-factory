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
package fr.irit.smac.amasfactory.agent;

import org.slf4j.Logger;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import fr.irit.smac.amasfactory.agent.features.IFeature;
import fr.irit.smac.amasfactory.agent.features.IFeatures;

/**
 * Interface that defines the interaction capabilities of an agent with the
 * infrastructure.
 *
 * @author lemouzy
 * @param <M>
 *            the generic type
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface IAgent<F extends IFeatures, K extends IKnowledge, S extends ISkill<K>, P extends IFeature<K, S>> {

    public void setLogger(Logger logger);

    public F getFeatures();

    public P getPrimaryFeature();

}
