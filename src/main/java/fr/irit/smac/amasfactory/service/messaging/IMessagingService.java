package fr.irit.smac.amasfactory.service.messaging;

import fr.irit.smac.amasfactory.message.IMessage;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.libs.tooling.messaging.IMsgService;

/**
 * The Interface IMessagingService exposes methods to use the messaging service.
 *
 * @param <M> the generic type
 */
@SuppressWarnings("hiding")
public interface IMessagingService<IMessage> extends IInfraService, IMsgService<IMessage> {

}
