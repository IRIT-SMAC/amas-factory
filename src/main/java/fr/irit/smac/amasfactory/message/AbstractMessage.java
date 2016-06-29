package fr.irit.smac.amasfactory.message;

import java.io.Serializable;

public class AbstractMessage implements IMessage, Serializable{

    private static final long serialVersionUID = 1902330088251578329L;
    final EMessageType messageType;
	
	public EMessageType getMessageType(){
		return this.messageType;
	}
	
	public AbstractMessage(EMessageType messageType){
		this.messageType = messageType;
	}
	
    @Override
    public String toString() {
        return "Message type: " + messageType.name();
    }
}
