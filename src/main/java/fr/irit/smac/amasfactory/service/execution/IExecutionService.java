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
package fr.irit.smac.amasfactory.service.execution;

import java.util.concurrent.Future;

import fr.irit.smac.amasfactory.impl.ShutdownRuntimeException;
import fr.irit.smac.amasfactory.service.IService;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.libs.tooling.scheduling.IHookHandler;

/**
 * The Interface IExecutionService exposes methods to control the execution of
 * the multi-agent system.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
public interface IExecutionService<A>
    extends IAgentEventListener<A>, IService, IHookHandler {
    /**
     * Launches the continuous execution of the system. The system will execute
     * according to the implemented strategy, until the {@link #pause} method is
     * called.
     * <p>
     * This method should return immediately.
     * <p>
     * The parameter <code>milis</code> can be used by the various
     * implementations in order to introduce specific effects.
     * 
     * @param milis
     *            an optional parameter for controlling the execution speed,
     *            whose exact effect depends on the implementation
     */
    public void run(long milis);

    /**
     * Launches an atomic execution of the system. What is exactly an atomic
     * execution is defined by the implemented strategy.
     * 
     * Implementations should ensure that this method is non-blocking, with the
     * effect of waiting on the returned {@link Future} similar to a blocking
     * method.
     * 
     * @return a {@link Future} which will complete when the step finishes
     */
    public Future<?> step();

    /**
     * Pause the execution of the system if it is currently running. Otherwise
     * it should have no effect.
     * 
     * Implementations should ensure that this method is non-blocking, with the
     * effect of waiting on the returned {@link Future} similar to a blocking
     * method.
     * 
     * @return a {@link Future} which will complete when the system is
     *         effectively paused
     */
    public Future<?> pause();

    /**
     * Shutdown the system definitively.
     * 
     * Implementations should ensure that this method is blocking
     *
     * @throws ShutdownRuntimeException
     *             the shutdown runtime exception
     */
    @Override
    public void shutdown() throws ShutdownRuntimeException;
    
    public void setAgentHandlerService(IAgentHandlerService<A> agentHandlerService);

    public void displaySimpleGui();

}
