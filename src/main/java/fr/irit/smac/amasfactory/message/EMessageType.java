package fr.irit.smac.amasfactory.message;

public enum EMessageType implements IMessageType {
    SEND_TO_TARGET_MESSAGE("sendToTargetMessage"), SEND_PORT_TO_TARGET_MESSAGE("sendPortToTargetMessage"), SIMPLE("simple");

    private String name;

    private EMessageType(final String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
}
