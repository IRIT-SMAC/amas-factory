package fr.irit.smac.amasfactory.service.logging.impl;

import java.util.Map;

import org.slf4j.Logger;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.libs.tooling.logging.AgentLog;

public class AgentLogLoggingService<M> extends   AbstractInfraService< IInfrastructureAgent<M>, M> implements ILoggingService<M>{

    @Override
    public Logger getAgentLogger(String loggerName) {
        return AgentLog.getAgentLogger(loggerName);
    }
    
    @Override
    public Logger getStandardLogger(String loggerName) {
       return AgentLog.getStandardLogger(loggerName);
    }

    @Override
    protected void initParameters(Map<String, Object> parameters) {
        // NOTHING to do
    }

    @Override
    public void start() {
        AgentLog.initializer()
            .logEnabled(true)
            .clearLogFolderOnStartup(true)
            .logFolderName("target/log")
            .initialize();
        
        this.getInfrastructure().getExecutionService().addPreStepHook( new Runnable() {
            private int stepNum = 0;
            @Override
            public void run() {
                AgentLog.setAmasStepNumber(stepNum++);
            }
        });
    }

    @Override
    public void shutdown() {
        // NOTHING to do
        
    }
}
