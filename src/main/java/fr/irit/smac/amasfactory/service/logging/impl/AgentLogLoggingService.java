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
package fr.irit.smac.amasfactory.service.logging.impl;

import org.slf4j.Logger;

import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.libs.tooling.logging.AgentLog;

/**
 * AgentLogLoggingService is a service which allows to write logs about the agents.
 *
 * @param <A> the type of the agents
 */
public class AgentLogLoggingService<A> implements ILoggingService<A> {

    private IExecutionService<A> executionService;

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.logging.ILoggingService#getAgentLogger(java.lang.String)
     */
    @Override
    public Logger getAgentLogger(String loggerName) {
        return AgentLog.getAgentLogger(loggerName);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.logging.ILoggingService#getStandardLogger(java.lang.String)
     */
    @Override
    public Logger getStandardLogger(String loggerName) {
        return AgentLog.getStandardLogger(loggerName);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.IInfraService#start()
     */
    @Override
    public void start() {
        AgentLog.initializer()
            .logEnabled(true)
            .clearLogFolderOnStartup(true)
            .logFolderName("target/log")
            .initialize();

        executionService.addPreStepHook(new Runnable() {
            private int stepNum = 0;

            @Override
            public void run() {
                AgentLog.setAmasStepNumber(stepNum++);
            }
        });
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.IInfraService#shutdown()
     */
    @Override
    public void shutdown() {
        // NOTHING to do

    }

    @Override
    public void setExecutionService(IExecutionService<A> executionService) {
        this.executionService = executionService;
    }
}
