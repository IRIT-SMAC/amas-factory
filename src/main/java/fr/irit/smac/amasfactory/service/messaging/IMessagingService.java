package fr.irit.smac.amasfactory.service.messaging;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.libs.tooling.messaging.IMsgService;

/**
 * The Interface IMessagingService exposes methods to use the messaging service.
 *
 * @param <M> the generic type
 */
public interface IMessagingService<M> extends IInfraService<IAgent<M>, M>, IMsgService<M> {

}
