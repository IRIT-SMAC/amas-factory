package fr.irit.smac.amasfactory.message;

public class ValuePortMessage extends Message {

    private static final long serialVersionUID = 4427304128676404559L;
    private final Object value;
    private final String port;

    public ValuePortMessage(String port, Object value, String sender) {

        super(EMessageType.SEND_TO_TARGET_MESSAGE, sender);

        this.port = port;
        this.value = value;
    }

    public Object getValue() {

        return this.value;
    }

    public String getPort() {

        return this.port;
    }

    @Override
    public String toString() {
        return "SendToTargetMessage [v=" + value + ", target=" + this.port + "]";
    }

}
