package fr.irit.smac.amasfactory.service.execution.impl;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.impl.ShutdownRuntimeException;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.TwoStepsSystemStrategy;
import fr.irit.smac.libs.tooling.scheduling.impl.system.SynchronizedSystemStrategy;

public class TwoStepAgExecutionService<A extends ITwoStepsAgent & IInfrastructureAgent<M>, M>
    extends AbstractInfraService<A, M>implements IExecutionService<A, M> {

    private TwoStepsSystemStrategy systemStrategy;
    private static final Logger      LOGGER   = Logger.getLogger(SynchronizedSystemStrategy.class.getName());

    @JsonProperty
    private int nbThreads;

    public TwoStepAgExecutionService() {
        super();
        this.systemStrategy = null;
        this.nbThreads = Integer.MIN_VALUE;
    }

    @Override
    public void start() {
        // first create the scheduler instance before registering
        // as agent listener (in order to assure that agentAdded and
        // agentRemoved
        // will not be called while this.systemStrategy = null)

        this.systemStrategy = new TwoStepsSystemStrategy(Collections.emptyList(),
            Executors.newFixedThreadPool(this.nbThreads));

        this.getInfrastructure().getAgentHandler().addAgentEventListener(this);
    }

    @Override
    public void run(long milis) {
        this.systemStrategy.run(milis);
    }

    @Override
    public Future<?> step() {
        return this.systemStrategy.step();
    }

    @Override
    public Future<?> pause() {
        return this.systemStrategy.step();
    }

    @Override
    public void shutdown() throws ShutdownRuntimeException {
        try {
            this.systemStrategy.shutdown().get();
        }
        catch (InterruptedException | ExecutionException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            throw new ShutdownRuntimeException("Exception during shutdown of TwoStepAgentExecutionService");
        }
    }

    @Override
    public void agentAdded(A agent) {
        this.systemStrategy.addAgent(agent);
    }

    @Override
    public void agentRemoved(A agent) {
        this.systemStrategy.removeAgent(agent);
    }

    @Override
    public void addPreStepHook(Runnable task) {
        this.systemStrategy.addPreStepHook(task);
    }

    @Override
    public void addPostStepHook(Runnable task) {
        this.systemStrategy.addPostStepHook(task);
    }

    @Override
    public void removePreStepHook(Runnable task) {
        this.systemStrategy.removePreStepHook(task);
    }

    @Override
    public void removePostStepHook(Runnable task) {
        this.systemStrategy.removePostStepHook(task);
    }

    @Override
    public Set<Runnable> getPreStepHooks() {
        return this.getPreStepHooks();
    }

    @Override
    public Set<Runnable> getPostStepHooks() {
        return this.getPostStepHooks();
    }
}
