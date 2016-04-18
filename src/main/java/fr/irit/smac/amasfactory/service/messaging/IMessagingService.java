package fr.irit.smac.amasfactory.service.messaging;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;
import fr.irit.smac.libs.tooling.messaging.IMsgService;

public interface IMessagingService<M> extends IInfraService<IInfrastructureAgent<M>, M>, IMsgService<M> {

}
