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
package fr.irit.smac.amasfactory.service.logging;

import org.slf4j.Logger;

import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;

/**
 * The Interface ILoggingService exposes methods to use the logger service.
 *
 * @param <M> the generic type
 */
public interface ILoggingService<A> extends IInfraService {
    
    /**
     * Gets the standard logger.
     *
     * @param loggerName the logger name
     * @return the standard logger
     */
    public Logger getStandardLogger(String loggerName);

    /**
     * Gets the agent logger.
     *
     * @param loggerName the logger name
     * @return the agent logger
     */
    public Logger getAgentLogger(String loggerName);


    public void setExecutionService(IExecutionService<A> executionService);

}
