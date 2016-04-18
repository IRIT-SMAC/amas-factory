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

/**
 * MessagingService is a service which handles the communication of the agents.
 *
 * @param <M> the generic type
 */
public class MessagingService<M> extends AbstractInfraService<IInfrastructureAgent<M>, M>
    implements IMessagingService<M>, IMsgService<M> {

    private IMsgService<M> delegatedMsgService;

    private Class<M> messageClass;

    /**
     * Instantiates a new messaging service.
     *
     * @param messageClass the message class
     */
    @JsonCreator
    public MessagingService(@JsonProperty(value = "messageClass", required = true) Class<M> messageClass) {
        super();
        this.messageClass = messageClass;
        this.delegatedMsgService = null;
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.IInfraService#start()
     */
    @Override
    public void start() {
        this.delegatedMsgService = AgentMessaging.getMsgService(messageClass);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.amasfactory.service.IInfraService#shutdown()
     */
    @Override
    public void shutdown() {
        AgentMessaging.shutdownMsgService(this.messageClass);
        this.delegatedMsgService = null;
        this.messageClass = null;
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.libs.tooling.messaging.ISender#getDirectory()
     */
    @Override
    public IDirectory<M> getDirectory() {
        return this.delegatedMsgService.getDirectory();
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.libs.tooling.messaging.ISender#send(java.lang.Object, java.lang.String)
     */
    @Override
    public boolean send(M msg, String receiverId) {
        return this.delegatedMsgService.send(msg, receiverId);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.libs.tooling.messaging.ISender#send(java.lang.Object, fr.irit.smac.libs.tooling.messaging.impl.Ref)
     */
    @Override
    public boolean send(M msg, Ref<M> receiverRef) {
        return this.delegatedMsgService.send(msg, receiverRef);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.libs.tooling.messaging.ISender#sendToGroup(java.lang.Object, java.lang.String)
     */
    @Override
    public boolean sendToGroup(M msg, String groupId) {
        return this.delegatedMsgService.sendToGroup(msg, groupId);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.libs.tooling.messaging.ISender#sendToGroup(java.lang.Object, fr.irit.smac.libs.tooling.messaging.impl.Ref)
     */
    @Override
    public boolean sendToGroup(M msg, Ref<M> groupRef) {
        return this.delegatedMsgService.sendToGroup(msg, groupRef);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.libs.tooling.messaging.ISender#broadcast(java.lang.Object)
     */
    @Override
    public boolean broadcast(M msg) {
        return this.delegatedMsgService.broadcast(msg);
    }

    /* (non-Javadoc)
     * @see fr.irit.smac.libs.tooling.messaging.IMsgService#getMsgBox(java.lang.String)
     */
    @Override
    public IMsgBox<M> getMsgBox(String agentId) {
        return this.delegatedMsgService.getMsgBox(agentId);
    }

}
