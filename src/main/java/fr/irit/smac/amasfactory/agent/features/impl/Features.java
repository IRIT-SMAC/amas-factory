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

import fr.irit.smac.amasfactory.agent.features.IFeature;
import fr.irit.smac.amasfactory.agent.features.IFeatures;
import fr.irit.smac.amasfactory.agent.features.basic.IKnowledgeBasic;
import fr.irit.smac.amasfactory.agent.features.basic.ISkillBasic;
import fr.irit.smac.amasfactory.agent.features.social.IKnowledgeSocial;
import fr.irit.smac.amasfactory.agent.features.social.ISkillSocial;

public class Features implements IFeatures {

    @JsonProperty
    public IFeature<IKnowledgeSocial, ISkillSocial<IKnowledgeSocial>> featureSocial;

    @JsonProperty
    public IFeature<IKnowledgeBasic, ISkillBasic<IKnowledgeBasic>> featureBasic;

    public Features() {

    }

    @Override
    public IFeature<IKnowledgeSocial, ISkillSocial<IKnowledgeSocial>> getFeatureSocial() {

        return featureSocial;
    }

    @Override
    public IFeature<IKnowledgeBasic, ISkillBasic<IKnowledgeBasic>> getFeatureBasic() {

        return featureBasic;
    }

    @Override
    public void setFeatureSocial(IFeature<IKnowledgeSocial, ISkillSocial<IKnowledgeSocial>> featureSocial) {
        this.featureSocial = featureSocial;
    }

    @Override
    public void setFeatureBasic(IFeature<IKnowledgeBasic, ISkillBasic<IKnowledgeBasic>> featureBasic) {
        this.featureBasic = featureBasic;
    }
    
    
}
