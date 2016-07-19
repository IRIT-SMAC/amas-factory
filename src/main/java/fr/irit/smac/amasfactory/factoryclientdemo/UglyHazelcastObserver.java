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
package fr.irit.smac.amasfactory.factoryclientdemo;

import com.hazelcast.core.Hazelcast;

import fr.irit.smac.amasfactory.util.IHazelcastKnowledgeAccessor;
import fr.irit.smac.amasfactory.util.hazelcast.impl.HazelcastKnowledgeAccessor;

public class UglyHazelcastObserver {

    private UglyHazelcastObserver() {

    }

    public static final void main(String[] args) {

        IHazelcastKnowledgeAccessor accessor = new HazelcastKnowledgeAccessor();

        for (String id : accessor.getKnowledgeIdSet()) {
            System.out.println("id=" + id + "\tknowledge=" + accessor.getKnowledge(id) + "\tknoID="
                + accessor.getKnowledge(id).getId());
        }
        Hazelcast.shutdownAll();
    }
}
