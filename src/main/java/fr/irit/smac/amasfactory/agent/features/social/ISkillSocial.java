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

import fr.irit.smac.amasfactory.agent.ISkill;

public interface ISkillSocial<K extends IKnowledgeSocial> extends ISkill<K>{

    /**
     * Sends the output value of an agent to its targets
     */
    public void sendOutputValue(String id);

    /**
     * Send the name of its port to the target
     */
    public void sendPort(String id);

    /**
     * Add the targets according if the name of the port of an agent is received
     */
    public void addTargetFromMessage();

    /**
     * Updates the port if a value for this port is received
     */
    public void updatePortFromMessage();

    public void sendValueToTargets(String id);
}
