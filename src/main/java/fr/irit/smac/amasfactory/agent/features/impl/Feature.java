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
package fr.irit.smac.amasfactory.agent.features.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;
import fr.irit.smac.amasfactory.agent.features.IFeature;

public class Feature<K extends IKnowledge, S extends ISkill<K>> implements IFeature<K, S> {

    @JsonProperty
    protected K knowledge;

    @JsonProperty
    protected S skill;

    public Feature() {
        // Needed by Jackson
    }

    @Override
    public K getKnowledge() {
        return knowledge;
    }

    @Override
    public S getSkill() {
        return skill;
    }

    @JsonSetter("skill")
    public void setSkill(S skill) {
        this.skill = skill;
        skill.setKnowledge(knowledge);
    }
}
