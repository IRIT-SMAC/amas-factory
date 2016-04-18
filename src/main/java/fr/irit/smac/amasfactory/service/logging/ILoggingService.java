package fr.irit.smac.amasfactory.service.logging;

import org.slf4j.Logger;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;

public interface ILoggingService<M> extends IInfraService<IInfrastructureAgent<M>, M> {
    public Logger getStandardLogger(String loggerName);

    public Logger getAgentLogger(String loggerName);
}
