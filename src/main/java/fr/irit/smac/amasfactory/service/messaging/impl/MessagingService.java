package fr.irit.smac.amasfactory.service.messaging.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.impl.AbstractInfraService;
import fr.irit.smac.amasfactory.service.messaging.IMessagingService;
import fr.irit.smac.libs.tooling.messaging.AgentMessaging;
import fr.irit.smac.libs.tooling.messaging.IDirectory;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.messaging.IMsgService;
import fr.irit.smac.libs.tooling.messaging.impl.Ref;

public class MessagingService<M> extends AbstractInfraService<IInfrastructureAgent<M>, M>
    implements IMessagingService<M>, IMsgService<M> {

    private IMsgService<M> delegatedMsgService;

    private Class<M> messageClass;

    @JsonCreator
    public MessagingService(@JsonProperty(value = "messageClass", required = true) Class<M> messageClass) {
        super();
        this.messageClass = messageClass;
        this.delegatedMsgService = null;
    }

    @Override
    public void start() {
        this.delegatedMsgService = AgentMessaging.getMsgService(messageClass);
    }

    @Override
    public void shutdown() {
        AgentMessaging.shutdownMsgService(this.messageClass);
        this.delegatedMsgService = null;
        this.messageClass = null;
    }

    @Override
    public IDirectory<M> getDirectory() {
        return this.delegatedMsgService.getDirectory();
    }

    @Override
    public boolean send(M msg, String receiverId) {
        return this.delegatedMsgService.send(msg, receiverId);
    }

    @Override
    public boolean send(M msg, Ref<M> receiverRef) {
        return this.delegatedMsgService.send(msg, receiverRef);
    }

    @Override
    public boolean sendToGroup(M msg, String groupId) {
        return this.delegatedMsgService.sendToGroup(msg, groupId);
    }

    @Override
    public boolean sendToGroup(M msg, Ref<M> groupRef) {
        return this.delegatedMsgService.sendToGroup(msg, groupRef);
    }

    @Override
    public boolean broadcast(M msg) {
        return this.delegatedMsgService.broadcast(msg);
    }

    @Override
    public IMsgBox<M> getMsgBox(String agentId) {
        return this.delegatedMsgService.getMsgBox(agentId);
    }

}
