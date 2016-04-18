package fr.irit.smac.amasfactory;

import fr.irit.smac.amasfactory.service.logging.ILoggingService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;

public interface IAgentSideInfrastructure<M> {
    public IMessagingService<M> getMessagingService();

    public ILoggingService<M> getLoggingService();
}
