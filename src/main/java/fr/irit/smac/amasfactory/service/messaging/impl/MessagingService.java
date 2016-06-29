package fr.irit.smac.amasfactory.service.messaging.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import fr.irit.smac.amasfactory.service.messaging.IMessagingService;
import fr.irit.smac.libs.tooling.messaging.AgentMessaging;
import fr.irit.smac.libs.tooling.messaging.IDirectory;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.messaging.IMsgService;
import fr.irit.smac.libs.tooling.messaging.impl.Ref;

/**
 * MessagingService is a service which handles the communication of the agents.
 *
 * @param <M>
 *            the generic type
 */
public class MessagingService<IMessage>
    implements IMessagingService<IMessage>, IMsgService<IMessage> {

    private IMsgService<IMessage> delegatedMsgService;

    private Class<IMessage> messageClass;

    /**
     * Instantiates a new messaging service.
     *
     * @param messageClass
     *            the message class
     */
    @JsonCreator
    public MessagingService(@JsonProperty(value = "messageClass", required = true) Class<IMessage> messageClass) {
        super();
        this.messageClass = messageClass;
        this.delegatedMsgService = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#start()
     */
    @Override
    public void start() {

        // not consistent
        this.delegatedMsgService = AgentMessaging.getMsgService(messageClass);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#shutdown()
     */
    @Override
    public void shutdown() {
        AgentMessaging.shutdownMsgService(this.messageClass);
        this.delegatedMsgService = null;
        this.messageClass = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.messaging.ISender#getDirectory()
     */
    @Override
    public IDirectory<IMessage> getDirectory() {
        return this.delegatedMsgService.getDirectory();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.messaging.ISender#send(java.lang.Object,
     * java.lang.String)
     */
    @Override
    public boolean send(IMessage msg, String receiverId) {
        return this.delegatedMsgService.send(msg, receiverId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.messaging.ISender#send(java.lang.Object,
     * fr.irit.smac.libs.tooling.messaging.impl.Ref)
     */
    @Override
    public boolean send(IMessage msg, Ref<IMessage> receiverRef) {
        return this.delegatedMsgService.send(msg, receiverRef);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.messaging.ISender#sendToGroup(java.lang.Object,
     * java.lang.String)
     */
    @Override
    public boolean sendToGroup(IMessage msg, String groupId) {
        return this.delegatedMsgService.sendToGroup(msg, groupId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.messaging.ISender#sendToGroup(java.lang.Object,
     * fr.irit.smac.libs.tooling.messaging.impl.Ref)
     */
    @Override
    public boolean sendToGroup(IMessage msg, Ref<IMessage> groupRef) {
        return this.delegatedMsgService.sendToGroup(msg, groupRef);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.messaging.ISender#broadcast(java.lang.Object)
     */
    @Override
    public boolean broadcast(IMessage msg) {
        return this.delegatedMsgService.broadcast(msg);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.messaging.IMsgService#getMsgBox(java.lang.
     * String)
     */
    @Override
    public IMsgBox<IMessage> getMsgBox(String agentId) {
        return this.delegatedMsgService.getMsgBox(agentId);
    }

}
