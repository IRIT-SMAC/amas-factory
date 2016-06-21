package fr.irit.smac.amasfactory.message;

public class SendToTargetMessage extends AbstractMessage {

	private final Object value;
	private String portSource;
    private final String portTarget;
	
	public SendToTargetMessage(String portTarget, String portSource, Object value){
	
		super(EMessageType.SEND_TO_TARGET_MESSAGE);
		
		this.portTarget = portTarget;
		this.portSource = portSource;
		this.value = value;
	}
	
	public Object getValue() {
	    
	    return this.value;
	}
	
	public String getPortSource() {
	    
	    return this.portSource;
	}
	
	public String getPortTarget() {
	    
	    return this.portTarget;
	}

    @Override
    public String toString() {
        return "SendToTargetMessage [v=" + value + ", target=" + this.portTarget + ", source=" + this.portSource + "]";
    }

}
