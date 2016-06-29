package fr.irit.smac.amasfactory.service.logging.impl;

import org.slf4j.Logger;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.libs.tooling.logging.AgentLog;

/**
 * AgentLogLoggingService is a service which allows to write logs about the agents.
 *
 * @param <M> the generic type
 */
public class AgentLogLoggingService<A extends IAgent> implements ILoggingService<A> {

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

        this.executionService.addPreStepHook(new Runnable() {
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
