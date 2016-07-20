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
package fr.irit.smac.amasfactory.service.execution.impl;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.impl.ShutdownRuntimeException;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.libs.tooling.scheduling.contrib.gui.SystemControllerPanel;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.TwoStepsSystemStrategy;
import fr.irit.smac.libs.tooling.scheduling.impl.system.SynchronizedSystemStrategy;

/**
 * TwoStepAgExecutionService is a service which handles the execution of the
 * multi-agent system.
 *
 * @param <A>
 *            the generic type
 * @param <M>
 *            the generic type
 */
public class TwoStepAgExecutionService<A extends ITwoStepsAgent> implements IExecutionService<A>{

    private TwoStepsSystemStrategy systemStrategy;

    private static final Logger LOGGER = Logger.getLogger(SynchronizedSystemStrategy.class.getName());

    @JsonProperty
    private int nbThreads;
    
    private IAgentHandlerService<A> agentHandlerService;

    /**
     * Instantiates a new two step ag execution service.
     */
    public TwoStepAgExecutionService() {
        super();
        systemStrategy = null;
        nbThreads = Integer.MIN_VALUE;
    }
    
    @Override
    public void setNbThreads(int nbThreads) {
        this.nbThreads = nbThreads;
    }

    @Override
    public void setAgentHandlerService(IAgentHandlerService<A> agentHandlerService) {
        this.agentHandlerService = agentHandlerService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#start()
     */
    @Override
    public void start() {
        // first create the scheduler instance before registering
        // as agent listener (in order to assure that agentAdded and
        // agentRemoved
        // will not be called while this.systemStrategy = null)

        systemStrategy = new TwoStepsSystemStrategy(Collections.emptyList(),
            Executors.newFixedThreadPool(nbThreads));
        
        agentHandlerService.addAgentEventListener(this);
    }

    @Override
    public void displaySimpleGui() {
        
        JFrame frame = new JFrame() {
            {
                add(new SystemControllerPanel(systemStrategy, 500));
                addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        systemStrategy.shutdown();
                    }
                });
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        };
        frame.pack();
        frame.setVisible(true);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.amasfactory.service.execution.IExecutionService#run(long)
     */
    @Override
    public void run(long milis) {
        systemStrategy.run(milis);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.execution.IExecutionService#step()
     */
    @Override
    public Future<?> step() {
        return systemStrategy.step();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.execution.IExecutionService#pause()
     */
    @Override
    public Future<?> pause() {
        return systemStrategy.step();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#shutdown()
     */
    @Override
    public void shutdown() throws ShutdownRuntimeException {
        try {
            systemStrategy.shutdown().get();
        }
        catch (InterruptedException | ExecutionException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            throw new ShutdownRuntimeException("Exception during shutdown of TwoStepAgentExecutionService");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener#
     * agentAdded(java.lang.Object)
     */
    @Override
    public void agentAdded(A agent) {
        systemStrategy.addAgent(agent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.agenthandler.IAgentEventListener#
     * agentRemoved(java.lang.Object)
     */
    @Override
    public void agentRemoved(A agent) {
        systemStrategy.removeAgent(agent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.scheduling.IHookHandler#addPreStepHook(java.
     * lang.Runnable)
     */
    @Override
    public void addPreStepHook(Runnable task) {
        systemStrategy.addPreStepHook(task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.scheduling.IHookHandler#addPostStepHook(java.
     * lang.Runnable)
     */
    @Override
    public void addPostStepHook(Runnable task) {
        systemStrategy.addPostStepHook(task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.scheduling.IHookHandler#removePreStepHook(java.
     * lang.Runnable)
     */
    @Override
    public void removePreStepHook(Runnable task) {
        systemStrategy.removePreStepHook(task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.scheduling.IHookHandler#removePostStepHook(java
     * .lang.Runnable)
     */
    @Override
    public void removePostStepHook(Runnable task) {
        systemStrategy.removePostStepHook(task);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.scheduling.IHookHandler#getPreStepHooks()
     */
    @Override
    public Set<Runnable> getPreStepHooks() {
        return getPreStepHooks();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.scheduling.IHookHandler#getPostStepHooks()
     */
    @Override
    public Set<Runnable> getPostStepHooks() {
        return getPostStepHooks();
    }
}
