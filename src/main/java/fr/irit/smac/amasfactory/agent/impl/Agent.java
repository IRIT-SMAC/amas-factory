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
package fr.irit.smac.amasfactory.agent.impl;

import org.slf4j.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;
import fr.irit.smac.amasfactory.agent.features.ICommonFeatures;

/**
 * The implementation of an agent
 *
 * @param <F>
 *            the common features
 * @param <K>
 *            the knowledge
 * @param <S>
 *            the skill
 */
@JsonPropertyOrder(alphabetic = true)
public abstract class Agent<F extends ICommonFeatures, K extends IKnowledge, S extends ISkill<K>>
    implements IAgent<F, K, S> {

    @JsonProperty
    protected F commonFeatures;

    @JsonProperty
    protected K knowledge;

    @JsonProperty
    protected S skill;

    protected Logger logger;

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
        this.skill.setLogger(logger);
        this.commonFeatures.getFeatureBasic().getSkill().setLogger(logger);
        this.commonFeatures.getFeatureSocial().getSkill().setLogger(logger);
    }

    @Override
    public F getFeatures() {
        return commonFeatures;
    }

    @Override
    public void setCommonFeatures(F commonFeatures) {
        this.commonFeatures = commonFeatures;
    }

    @Override
    public K getKnowledge() {
        return knowledge;
    }

    @JsonSetter("knowledge")
    @Override
    public void setKnowledge(K knowledge) {
        this.knowledge = knowledge;
        if (skill != null) {
            skill.setKnowledge(knowledge);
        }
    }

    @Override
    public S getSkill() {
        return skill;
    }

    @JsonSetter("skill")
    @Override
    public void setSkill(S skill) {
        this.skill = skill;
        if (knowledge != null) {
            this.skill.setKnowledge(knowledge);
        }
    }

}
