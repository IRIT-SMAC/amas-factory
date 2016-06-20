package fr.irit.smac.amasfactory.message;

public class AbstractMessage implements IMessage{

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
