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
package fr.irit.smac.amasfactory.util.hazelcast.impl;

import java.util.Map;
import java.util.Set;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import fr.irit.smac.amasfactory.agent.features.basic.IKnowledgeBasic;
import fr.irit.smac.amasfactory.util.hazelcast.IHazelcastKnowledgeAccessor;

/**
 * Class allowing to connect to the shared knowledge map and to to basic
 * operation on it.
 * 
 * @author SVI
 *
 */
public class HazelcastKnowledgeAccessor implements IHazelcastKnowledgeAccessor {

    Map<String, IKnowledgeBasic> mapKnowledge;

    HazelcastInstance instance;

    public HazelcastKnowledgeAccessor() {
        Config cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
        mapKnowledge = instance.getMap("knowledge");

    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.util.impl.IHazelcastKnowledgeAccessor#
     * registerKnowledge(fr.irit.smac.amasfactory.agent.IKnowledge)
     */
    @Override
    public void registerKnowledge(IKnowledgeBasic knowledge) {
        mapKnowledge.put(knowledge.getId(), knowledge);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.util.impl.IHazelcastKnowledgeAccessor#
     * removeKnowledge(java.lang.String)
     */
    @Override
    public void removeKnowledge(String knowledgeId) {
        mapKnowledge.remove(knowledgeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.util.impl.IHazelcastKnowledgeAccessor#
     * getKnowledge(java.lang.String)
     */
    @Override
    public IKnowledgeBasic getKnowledge(String knowledgeId) {
        return mapKnowledge.get(knowledgeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.util.impl.IHazelcastKnowledgeAccessor#
     * getKnowledgeIdSet()
     */
    @Override
    public Set<String> getKnowledgeIdSet() {
        return mapKnowledge.keySet();
    }

    @Override
    public Map<String, IKnowledgeBasic> getKnowledgeMap() {
        return mapKnowledge;
    }

    @Override
    public void shutdownInstance() {
        instance.shutdown();
    }
}
