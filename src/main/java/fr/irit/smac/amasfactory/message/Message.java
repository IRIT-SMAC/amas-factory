package fr.irit.smac.amasfactory.message;

import java.io.Serializable;

public class Message implements IMessage, Serializable{

    private static final long serialVersionUID = 1902330088251578329L;
    final IMessageType messageType;
    protected String sender;

    
	public Message(IMessageType messageType, String sender){
		this.messageType = messageType;
		this.sender = sender;
	}
	
    @Override
    public String toString() {
        return "Message type: " + messageType.getName();
    }
    
    public IMessageType getMessageType(){
        return this.messageType;
    }
    
    @Override
    public String getSender() {
        return sender;
    }
}
