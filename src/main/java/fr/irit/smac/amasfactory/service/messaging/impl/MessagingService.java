/*
 * #%L
 * amas-factory
 * %%
 * Copyright (C) 2015 - 2016 IRIT - SMAC Team and Brennus Analytics
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package fr.irit.smac.amasfactory.service.messaging.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import fr.irit.smac.amasfactory.message.IMessage;
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
 *            the type of the message
 */
public class MessagingService<M extends IMessage>
    implements IMessagingService<M>, IMsgService<M> {

    private IMsgService<M> delegatedMsgService;

    private Class<M> messageClass;

    private static final Logger LOGGER = Logger.getLogger(MessagingService.class.getName());

    /**
     * Instantiates a new messaging service.
     *
     * @param messageClass
     *            the message class
     */
    @SuppressWarnings("unchecked")
    public MessagingService() {
        super();
        try {
            messageClass = (Class<M>) Class.forName("fr.irit.smac.amasfactory.message.Message");
        }
        catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "An error occured during the initialisation of the messagingService", e);
        }

        delegatedMsgService = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#start()
     */
    @Override
    public void start() {

        // not consistent
        delegatedMsgService = AgentMessaging.getMsgService(messageClass);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.amasfactory.service.IInfraService#shutdown()
     */
    @Override
    public void shutdown() {
        AgentMessaging.shutdownMsgService(messageClass);
        delegatedMsgService = null;
        messageClass = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.messaging.ISender#getDirectory()
     */
    @Override
    public IDirectory<M> getDirectory() {
        return delegatedMsgService.getDirectory();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.messaging.ISender#send(java.lang.Object,
     * java.lang.String)
     */
    @Override
    public boolean send(M msg, String receiverId) {
        return delegatedMsgService.send(msg, receiverId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.messaging.ISender#send(java.lang.Object,
     * fr.irit.smac.libs.tooling.messaging.impl.Ref)
     */
    @Override
    public boolean send(M msg, Ref<M> receiverRef) {
        return delegatedMsgService.send(msg, receiverRef);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.messaging.ISender#sendToGroup(java.lang.Object,
     * java.lang.String)
     */
    @Override
    public boolean sendToGroup(M msg, String groupId) {
        return delegatedMsgService.sendToGroup(msg, groupId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.messaging.ISender#sendToGroup(java.lang.Object,
     * fr.irit.smac.libs.tooling.messaging.impl.Ref)
     */
    @Override
    public boolean sendToGroup(M msg, Ref<M> groupRef) {
        return delegatedMsgService.sendToGroup(msg, groupRef);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.irit.smac.libs.tooling.messaging.ISender#broadcast(java.lang.Object)
     */
    @Override
    public boolean broadcast(M msg) {
        return delegatedMsgService.broadcast(msg);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.irit.smac.libs.tooling.messaging.IMsgService#getMsgBox(java.lang.
     * String)
     */
    @Override
    public IMsgBox<M> getMsgBox(String agentId) {
        return delegatedMsgService.getMsgBox(agentId);
    }

}
